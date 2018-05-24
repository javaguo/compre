package com.tgw.account.expendType.service.impl;


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

    public void checkBeforeDelete(List<String> idList) {
        if( getExpendTypeMapper().queryChildRecord( idList,PlatformUserUtils.getLoginUserInfo().getId() )>0 ){
            throw new PlatformException( "要删除的类型中有子支出类型，请先删除子支出类型！" );
        }

        if( getExpendTypeMapper().queryExpendRecord( idList,PlatformUserUtils.getLoginUserInfo().getId() )>0 ){
            throw new PlatformException( "支出记录中已使用了要删除的支出类型，无法删除！若确定要删除支出类型，请先删除相应的支出记录！" );
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
