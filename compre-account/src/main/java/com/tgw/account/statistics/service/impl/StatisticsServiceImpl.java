package com.tgw.account.statistics.service.impl;


import com.tgw.account.statistics.dao.StatisticsMapper;
import com.tgw.account.statistics.service.StatisticsService;
import com.tgw.basic.framework.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("statisticsService")
public class StatisticsServiceImpl extends BaseServiceImpl implements StatisticsService {
    @Resource
    private StatisticsMapper statisticsMapper;

    @Override
    public void initMapper() {
        /**
        * 具体业务service层必须覆写此方法
        */
        if( null!=statisticsMapper ){
            super.setBaseModelMapper( this.getStatisticsMapper() );
        }
    }

    public StatisticsMapper getStatisticsMapper() {
        return statisticsMapper;
    }

    public void setStatisticsMapper(StatisticsMapper statisticsMapper) {
        this.statisticsMapper = statisticsMapper;
    }
}
