package com.tgw.account.expend.service.impl;


import com.tgw.account.expend.dao.ExpendMapper;
import com.tgw.account.expend.model.Expend;
import com.tgw.account.expend.service.ExpendService;
import com.tgw.basic.framework.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("expendService")
public class ExpendServiceImpl extends BaseServiceImpl implements ExpendService {
    @Resource
    private ExpendMapper expendMapper;

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

    public ExpendMapper getExpendMapper() {
        return expendMapper;
    }

    public void setExpendMapper(ExpendMapper expendMapper) {
        this.expendMapper = expendMapper;
    }
}
