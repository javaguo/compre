package com.tgw.omnipotent.comEventsInterval.service;


import com.tgw.basic.framework.service.BaseService;

import java.util.List;
import java.util.Map;


public interface ComEventsIntervalService extends BaseService {
    /**
     * 加载事件频率类型
     * @return
     */
    public abstract List<Map<String,Object>> loadEventIntervalComboBoxMap( );
}
