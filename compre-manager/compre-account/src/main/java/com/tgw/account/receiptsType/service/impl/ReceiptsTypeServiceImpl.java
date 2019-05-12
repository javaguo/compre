package com.tgw.account.receiptsType.service.impl;


import com.tgw.account.receipts.model.Receipts;
import com.tgw.account.receiptsType.dao.ReceiptsTypeMapper;
import com.tgw.account.receiptsType.model.ReceiptsType;
import com.tgw.account.receiptsType.service.ReceiptsTypeService;
import com.tgw.account.receiptsTypeSys.dao.ReceiptsTypeSysMapper;
import com.tgw.account.receiptsTypeSys.model.ReceiptsTypeSys;
import com.tgw.basic.common.exception.PlatformException;
import com.tgw.basic.framework.service.impl.BaseServiceImpl;
import com.tgw.basic.system.user.utils.PlatformUserUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("receiptsTypeService")
public class ReceiptsTypeServiceImpl extends BaseServiceImpl implements ReceiptsTypeService {
    @Resource
    private ReceiptsTypeMapper receiptsTypeMapper;
    @Resource
    private ReceiptsTypeSysMapper receiptsTypeSysMapper;

    @Override
    public void initMapper() {
        /**
        * 具体业务service层必须覆写此方法
        */
        if( null!=receiptsTypeMapper ){
            super.setBaseModelMapper( this.getReceiptsTypeMapper() );
        }
    }
    /**
     * 查询收入类型树节点数据
     * @return
     * @throws PlatformException
     */
    public List<Map<String,Object>> queryReceiptsTypeMap(String fkUserId)  throws PlatformException{
        return getReceiptsTypeMapper().queryReceiptsTypeMap(fkUserId);
    }

    public void saveReceiptsTypeFromSys() {
        ReceiptsType con = new ReceiptsType();
        con.setFkUserId( PlatformUserUtils.getLoginUserInfo().getId() );
        con.setIsSysOwn( 1 );

        int oldReceiptsTypeCount = this.getReceiptsTypeMapper().selectCount( con );
        if( oldReceiptsTypeCount>0 ){
            throw new PlatformException("已存在系统默认收入类型，不能重复生成！");
        }

        ReceiptsTypeSys conSys = new ReceiptsTypeSys();
        conSys.setFkParentId(-1);
        List<ReceiptsTypeSys> receiptsTypeSysList = this.getReceiptsTypeSysMapper().select( conSys );

        if( receiptsTypeSysList!=null && !receiptsTypeSysList.isEmpty() ){
            for( ReceiptsTypeSys receiptsTypeSys:receiptsTypeSysList ){
                saveReceiptsTypeSelfAndChild( receiptsTypeSys,-1 );
            }
        }
    }

    /**
     * 递归复制类型
     * @param receiptsTypeSys
     * @param newParentId
     */
    private void saveReceiptsTypeSelfAndChild( ReceiptsTypeSys receiptsTypeSys,int newParentId  ){
        ReceiptsType receiptsType = new ReceiptsType();
        receiptsType.setFkParentId( newParentId );//父ID用新ID
        receiptsType.setReceiptsTypeName( receiptsTypeSys.getReceiptsTypeName() );
        receiptsType.setOrderNum( receiptsTypeSys.getOrderNum() );
        receiptsType.setIsSysOwn( 1 );
        //expendType.setRemark( null );//备注字段不复制
        Date date = new Date();
        receiptsType.setAddTime( date );
        receiptsType.setUpdateTime( date );
        receiptsType.setFkUserId( PlatformUserUtils.getLoginUserInfo().getId() );

        //保存复制后的对象
        this.getReceiptsTypeMapper().insert( receiptsType );

        ReceiptsTypeSys conSys = new ReceiptsTypeSys();
        conSys.setFkParentId( receiptsTypeSys.getId() );
        List<ReceiptsTypeSys> receiptsTypeSysList = this.getReceiptsTypeSysMapper().select( conSys );
        if( receiptsTypeSysList!=null && !receiptsTypeSysList.isEmpty() ){
            for( ReceiptsTypeSys temp:receiptsTypeSysList ){
                saveReceiptsTypeSelfAndChild( temp,receiptsType.getId() );
            }
        }else{
            return ;
        }
    }

    public void beforeSaveBean(ReceiptsType bean) throws PlatformException {
        bean.setFkUserId( PlatformUserUtils.getLoginUserInfo().getId() );
        bean.setIsSysOwn(0);

        Date date = new Date();
        bean.setAddTime( date );
        bean.setUpdateTime( date );

        this.checkType(bean);
        this.checkOrderNum(bean,0);
    }

    public void beforeUpdateBean(ReceiptsType bean) throws PlatformException {
        this.checkType(bean);
        bean.setUpdateTime( new Date() );

        this.checkOrderNum(bean,1);
    }


    public void checkBeforeDelete(List<String> idList) {
        if( getReceiptsTypeMapper().queryChildRecord( idList,PlatformUserUtils.getLoginUserInfo().getId() )>0 ){
            throw new PlatformException( "要删除的类型中有子收入类型，请先删除子收入类型！" );
        }

        if( getReceiptsTypeMapper().queryReceiptsRecord( idList,PlatformUserUtils.getLoginUserInfo().getId() )>0 ){
            throw new PlatformException( "收入记录中已使用了要删除的收入类型，无法删除！若确定要删除收入类型，请先删除相应的收入记录！" );
        }
    }

    private void checkType(ReceiptsType bean)throws PlatformException{
        if( bean.getFkParentId()==null ){
            bean.setFkParentId(-1);
        }else{
            ReceiptsType type_p = new ReceiptsType();
            type_p.setId( bean.getFkParentId() );
            type_p = (ReceiptsType)this.selectUniqueBeanByPrimaryKey( type_p );
            if( type_p.getFkParentId()!=-1 ){
                throw new PlatformException("收入类型只支持二级结构，请选择一级节点作为父类型！");
            }
        }
    }

    private void checkOrderNum(ReceiptsType bean,int countMaxVal )throws PlatformException{
        if (bean.getFkParentId()==null){
            throw new PlatformException("父类型不能为空！");
        }
        if (bean.getOrderNum()==null){
            throw new PlatformException("序号不能为空！");
        }

        int count = this.getReceiptsTypeMapper().countByOrderNum(bean.getFkUserId(),bean.getFkParentId(),bean.getOrderNum());
        if (count>countMaxVal){
            if (bean.getFkParentId()==-1){
                throw new PlatformException("序号不能重复！一级类型中已存在相同序号！");
            }else{
                throw new PlatformException("序号不能重复！所属类型下已存在相同序号！");
            }
        }

    }


    public List<ReceiptsType> queryStatisticsReceiptsType(Receipts bean) {
        return getReceiptsTypeMapper().queryStatisticsReceiptsType( bean.getFkUserId(),bean.getIdList() );
    }

    public ReceiptsTypeMapper getReceiptsTypeMapper() {
        return receiptsTypeMapper;
    }

    public void setReceiptsTypeMapper(ReceiptsTypeMapper receiptsTypeMapper) {
        this.receiptsTypeMapper = receiptsTypeMapper;
    }

    public ReceiptsTypeSysMapper getReceiptsTypeSysMapper() {
        return receiptsTypeSysMapper;
    }

    public void setReceiptsTypeSysMapper(ReceiptsTypeSysMapper receiptsTypeSysMapper) {
        this.receiptsTypeSysMapper = receiptsTypeSysMapper;
    }
}
