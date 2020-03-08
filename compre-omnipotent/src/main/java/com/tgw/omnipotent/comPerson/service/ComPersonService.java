package com.tgw.omnipotent.comPerson.service;


import com.tgw.basic.common.exception.PlatformException;
import com.tgw.basic.framework.service.BaseService;

import java.util.List;
import java.util.Map;


public interface ComPersonService extends BaseService {
    /**
     * 加载通讯录人员
     * @return
     * @throws PlatformException
     */
    public abstract List<Map<String,Object>> loadComPersonComboBoxMap()  throws PlatformException;
}
