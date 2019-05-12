package com.tgw.account.expendType.service.impl;


import com.tgw.account.expend.model.Expend;
import com.tgw.account.expendType.dao.ExpendTypeMapper;
import com.tgw.account.expendType.model.ExpendType;
import com.tgw.account.expendType.service.ExpendTypeService;
import com.tgw.account.expendTypeSys.dao.ExpendTypeSysMapper;
import com.tgw.account.expendTypeSys.model.ExpendTypeSys;
import com.tgw.basic.common.exception.PlatformException;
import com.tgw.basic.framework.service.impl.BaseServiceImpl;
import com.tgw.basic.system.user.utils.PlatformUserUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("expendTypeService")
public class ExpendTypeServiceImpl extends BaseServiceImpl implements ExpendTypeService {
    @Resource
    private ExpendTypeMapper expendTypeMapper;
    @Resource
    private ExpendTypeSysMapper expendTypeSysMapper;

    @Override
    public void initMapper() {
        /**
        * 具体业务service层必须覆写此方法
        */
        if( null!=expendTypeMapper ){
            super.setBaseModelMapper( this.getExpendTypeMapper() );
        }
    }

    public List<Map<String, Object>> queryExpendTypeTreeMap(String fkUserId) throws PlatformException {
        return this.getExpendTypeMapper().queryExpendTypeTreeMap( fkUserId );
    }

    public void saveExpendTypeFromSys() {
        ExpendType con = new ExpendType();
        con.setFkUserId( PlatformUserUtils.getLoginUserInfo().getId() );
        con.setIsSysOwn( 1 );

        int oldExpendTypeCount = this.getExpendTypeMapper().selectCount( con );
        if( oldExpendTypeCount>0 ){
            throw new PlatformException("已存在系统默认支出类型，不能重复生成！");
        }

        ExpendTypeSys conSys = new ExpendTypeSys();
        conSys.setFkParentId(-1);
        List<ExpendTypeSys> expendTypeSysList = this.getExpendTypeSysMapper().select( conSys );

        if( expendTypeSysList!=null && !expendTypeSysList.isEmpty() ){
            for( ExpendTypeSys expendTypeSys:expendTypeSysList ){
                saveExpendTypeSelfAndChild( expendTypeSys,-1 );
            }
        }
    }

    /**
     * 递归复制类型
     * @param expendTypeSys
     * @param newParentId
     */
    private void saveExpendTypeSelfAndChild( ExpendTypeSys expendTypeSys,int newParentId  ){
        ExpendType expendType = new ExpendType();
        expendType.setFkParentId( newParentId );//父ID用新ID
        expendType.setExpendTypeName( expendTypeSys.getExpendTypeName() );
        expendType.setOrderNum( expendTypeSys.getOrderNum() );
        expendType.setIsSysOwn( 1 );
        //expendType.setRemark( null );//备注字段不复制
        Date date = new Date();
        expendType.setAddTime( date );
        expendType.setUpdateTime( date );
        expendType.setFkUserId( PlatformUserUtils.getLoginUserInfo().getId() );

        //保存复制后的对象
        this.getExpendTypeMapper().insert( expendType );

        ExpendTypeSys conSys = new ExpendTypeSys();
        conSys.setFkParentId( expendTypeSys.getId() );
        List<ExpendTypeSys> expendTypeSysList = this.getExpendTypeSysMapper().select( conSys );
        if( expendTypeSysList!=null && !expendTypeSysList.isEmpty() ){
            for( ExpendTypeSys temp:expendTypeSysList ){
                saveExpendTypeSelfAndChild( temp,expendType.getId() );
            }
        }else{
            return ;
        }
    }

    public void beforeSaveBean(ExpendType bean) throws PlatformException {
        bean.setFkUserId( PlatformUserUtils.getLoginUserInfo().getId() );
        bean.setIsSysOwn(0);

        Date date = new Date();
        bean.setAddTime( date );
        bean.setUpdateTime( date );

        this.checkType(bean);
        this.checkOrderNum(bean,0);
    }

    public void beforeUpdateBean(ExpendType bean) throws PlatformException {
        this.checkType(bean);
        bean.setUpdateTime( new Date() );

        this.checkOrderNum(bean,1);
    }

    public void checkBeforeDelete(List<String> idList) {
        if( getExpendTypeMapper().queryChildRecord( idList,PlatformUserUtils.getLoginUserInfo().getId() )>0 ){
            throw new PlatformException( "要删除的类型中有子支出类型，请先删除子支出类型！" );
        }

        if( getExpendTypeMapper().queryExpendRecord( idList,PlatformUserUtils.getLoginUserInfo().getId() )>0 ){
            throw new PlatformException( "支出记录中已使用了要删除的支出类型，无法删除！若确定要删除支出类型，请先删除相应的支出记录！" );
        }
    }

    public List<ExpendType> queryStatisticsExpendType(Expend bean) {
        return getExpendTypeMapper().queryStatisticsExpendType( bean.getFkUserId(),bean.getIdList() );
    }

    private void checkType(ExpendType bean)throws PlatformException{
        if( bean.getFkParentId()==null ){
            bean.setFkParentId(-1);
        }else{
            ExpendType type_p = new ExpendType();
            type_p.setId( bean.getFkParentId() );
            type_p = (ExpendType)this.selectUniqueBeanByPrimaryKey( type_p );
            if( type_p.getFkParentId()!=-1 ){
                throw new PlatformException("支出类型只支持二级结构，请选择一级节点作为父类型！");
            }
        }
    }

    private void checkOrderNum(ExpendType bean,int countMaxVal)throws PlatformException{
        if (bean.getFkParentId()==null){
            throw new PlatformException("父类型不能为空！");
        }
        if (bean.getOrderNum()==null){
            throw new PlatformException("序号不能为空！");
        }

        int count = this.getExpendTypeMapper().countByOrderNum(bean.getFkUserId(),bean.getFkParentId(),bean.getOrderNum());
        if (count>countMaxVal){
            if (bean.getFkParentId()==-1){
                throw new PlatformException("序号不能重复！一级类型中已存在相同序号！");
            }else{
                throw new PlatformException("序号不能重复！所属类型下已存在相同序号！");
            }
        }

    }

    public ExpendTypeMapper getExpendTypeMapper() {
        return expendTypeMapper;
    }

    public void setExpendTypeMapper(ExpendTypeMapper expendTypeMapper) {
        this.expendTypeMapper = expendTypeMapper;
    }

    public ExpendTypeSysMapper getExpendTypeSysMapper() {
        return expendTypeSysMapper;
    }

    public void setExpendTypeSysMapper(ExpendTypeSysMapper expendTypeSysMapper) {
        this.expendTypeSysMapper = expendTypeSysMapper;
    }
}
