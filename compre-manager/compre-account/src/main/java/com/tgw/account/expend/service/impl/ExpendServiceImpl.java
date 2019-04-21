package com.tgw.account.expend.service.impl;


import com.tgw.account.expend.dao.ExpendMapper;
import com.tgw.account.expend.model.Expend;
import com.tgw.account.expend.service.ExpendService;
import com.tgw.account.expendType.dao.ExpendTypeMapper;
import com.tgw.account.expendType.model.ExpendType;
import com.tgw.basic.common.exception.PlatformException;
import com.tgw.basic.framework.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("expendService")
public class ExpendServiceImpl extends BaseServiceImpl implements ExpendService {
    @Resource
    private ExpendMapper expendMapper;
    @Resource
    private ExpendTypeMapper expendTypeMapper;

    @Override
    public void initMapper() {
        /**
        * 具体业务service层必须覆写此方法
        */
        if( null!=expendMapper ){
            super.setBaseModelMapper( this.getExpendMapper() );
        }
    }

    public List<Map<String, Object>> queryStatisticsExpendData(Expend expend) {
        return getExpendMapper().queryStatisticsExpendData(expend);
    }

    public void checkExpendBeforSaveOrUpdate(Expend expend) throws PlatformException {
        ExpendType expendType = new ExpendType();
        expendType.setId( expend.getFkExpendTypeId() );
        expendType = getExpendTypeMapper().selectByPrimaryKey(expendType);
        if( expendType.getFkParentId()==-1 ){
            throw new PlatformException("支出类型只能选二级支出类型！");
        }
    }

    public ExpendMapper getExpendMapper() {
        return expendMapper;
    }

    public void setExpendMapper(ExpendMapper expendMapper) {
        this.expendMapper = expendMapper;
    }

    public ExpendTypeMapper getExpendTypeMapper() {
        return expendTypeMapper;
    }

    public void setExpendTypeMapper(ExpendTypeMapper expendTypeMapper) {
        this.expendTypeMapper = expendTypeMapper;
    }
}
