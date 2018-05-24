package com.tgw.omnipotent.comEvent.service;


import com.tgw.basic.common.exception.PlatformException;
import com.tgw.basic.framework.service.BaseService;

import java.util.List;
import java.util.Map;


public interface ComEventService extends BaseService {
    /**
     * 加载事件
     * @return
     * @throws PlatformException
     */
    public abstract List<Map<String,Object>> loadComEventComboBoxMap( )  throws PlatformException;
}
