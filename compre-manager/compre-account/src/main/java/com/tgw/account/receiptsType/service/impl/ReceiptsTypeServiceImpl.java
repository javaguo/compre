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

    public void checkBeforeDelete(List<String> idList) {
        if( getReceiptsTypeMapper().queryChildRecord( idList,PlatformUserUtils.getLoginUserInfo().getId() )>0 ){
            throw new PlatformException( "要删除的类型中有子收入类型，请先删除子收入类型！" );
        }

        if( getReceiptsTypeMapper().queryReceiptsRecord( idList,PlatformUserUtils.getLoginUserInfo().getId() )>0 ){
            throw new PlatformException( "收入记录中已使用了要删除的收入类型，无法删除！若确定要删除收入类型，请先删除相应的收入记录！" );
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
