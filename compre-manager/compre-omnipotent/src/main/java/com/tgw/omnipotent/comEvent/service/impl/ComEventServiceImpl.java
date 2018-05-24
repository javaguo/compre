package com.tgw.omnipotent.comEvent.service.impl;


import com.tgw.basic.common.exception.PlatformException;
import com.tgw.basic.framework.service.impl.BaseServiceImpl;
import com.tgw.basic.system.user.utils.PlatformUserUtils;
import com.tgw.omnipotent.comEvent.dao.ComEventMapper;
import com.tgw.omnipotent.comEvent.service.ComEventService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Service("comEventService")
public class ComEventServiceImpl extends BaseServiceImpl implements ComEventService {
    @Resource
    private ComEventMapper comEventMapper;

    @Override
    public void initMapper() {
        /**
        * 具体业务service层必须覆写此方法
        */
        if( null!=comEventMapper ){
            super.setBaseModelMapper( this.getComEventMapper() );
        }
    }

    public List<Map<String, Object>> loadComEventComboBoxMap() throws PlatformException {
        Calendar calendar = Calendar.getInstance();
        calendar.add( Calendar.MONTH,-12 );

        return this.getComEventMapper().loadComEventComboBoxMap(PlatformUserUtils.getLoginUserInfo().getId(), calendar.getTime() );
    }

    public ComEventMapper getComEventMapper() {
        return comEventMapper;
    }

    public void setComEventMapper(ComEventMapper comEventMapper) {
        this.comEventMapper = comEventMapper;
    }
}
