package com.tgw.account.expendTypeSys.service.impl;


import com.tgw.account.expendTypeSys.dao.ExpendTypeSysMapper;
import com.tgw.account.expendTypeSys.service.ExpendTypeSysService;
import com.tgw.basic.common.exception.PlatformException;
import com.tgw.basic.framework.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("expendTypeSysService")
public class ExpendTypeSysServiceImpl extends BaseServiceImpl implements ExpendTypeSysService {
    @Resource
    private ExpendTypeSysMapper expendTypeSysMapper;

    @Override
    public void initMapper() {
        /**
        * 具体业务service层必须覆写此方法
        */
        if( null!=expendTypeSysMapper ){
            super.setBaseModelMapper( this.getExpendTypeSysMapper() );
        }
    }

    public List<Map<String, Object>> queryExpendTypeSysTreeMap() throws PlatformException {
        return this.getExpendTypeSysMapper().queryExpendTypeSysTreeMap();
    }

    public void checkBeforeDelete(List<String> idList) {
        if( getExpendTypeSysMapper().queryChildRecord( idList )>0 ){
            throw new PlatformException( "要删除的类型中有子支出类型，请先删除子支出类型！" );
        }
    }

    public ExpendTypeSysMapper getExpendTypeSysMapper() {
        return expendTypeSysMapper;
    }

    public void setExpendTypeSysMapper(ExpendTypeSysMapper expendTypeSysMapper) {
        this.expendTypeSysMapper = expendTypeSysMapper;
    }
}
