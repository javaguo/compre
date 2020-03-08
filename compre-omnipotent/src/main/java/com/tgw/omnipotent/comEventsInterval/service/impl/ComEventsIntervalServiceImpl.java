package com.tgw.omnipotent.comEventsInterval.service.impl;


import com.tgw.basic.framework.service.impl.BaseServiceImpl;
import com.tgw.basic.system.user.model.SysEnUser;
import com.tgw.basic.system.user.utils.PlatformUserUtils;
import com.tgw.omnipotent.comEventsInterval.dao.ComEventsIntervalMapper;
import com.tgw.omnipotent.comEventsInterval.service.ComEventsIntervalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("comEventsIntervalService")
public class ComEventsIntervalServiceImpl extends BaseServiceImpl implements ComEventsIntervalService {
    @Resource
    private ComEventsIntervalMapper comEventsIntervalMapper;

    @Override
    public void initMapper() {
        /**
        * 具体业务service层必须覆写此方法
        */
        if( null!=comEventsIntervalMapper ){
            super.setBaseModelMapper( this.getComEventsIntervalMapper() );
        }
    }

    public List<Map<String, Object>> loadEventIntervalComboBoxMap() {
        SysEnUser u = PlatformUserUtils.getLoginUserInfo();
        return this.getComEventsIntervalMapper().loadEventIntervalComboBoxMap( u.getId() );
    }

    public ComEventsIntervalMapper getComEventsIntervalMapper() {
        return comEventsIntervalMapper;
    }

    public void setComEventsIntervalMapper(ComEventsIntervalMapper comEventsIntervalMapper) {
        this.comEventsIntervalMapper = comEventsIntervalMapper;
    }
}
