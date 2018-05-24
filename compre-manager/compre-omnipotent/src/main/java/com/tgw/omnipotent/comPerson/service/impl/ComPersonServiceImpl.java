package com.tgw.omnipotent.comPerson.service.impl;


import com.tgw.basic.common.exception.PlatformException;
import com.tgw.basic.framework.service.impl.BaseServiceImpl;
import com.tgw.basic.system.user.utils.PlatformUserUtils;
import com.tgw.omnipotent.comPerson.dao.ComPersonMapper;
import com.tgw.omnipotent.comPerson.service.ComPersonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("comPersonService")
public class ComPersonServiceImpl extends BaseServiceImpl implements ComPersonService {
    @Resource
    private ComPersonMapper comPersonMapper;

    @Override
    public void initMapper() {
        /**
        * 具体业务service层必须覆写此方法
        */
        if( null!=comPersonMapper ){
            super.setBaseModelMapper( this.getComPersonMapper() );
        }
    }

    public List<Map<String, Object>> loadComPersonComboBoxMap() throws PlatformException {
        return getComPersonMapper().loadComPersonComboBoxMap(PlatformUserUtils.getLoginUserInfo().getId() );
    }

    public ComPersonMapper getComPersonMapper() {
        return comPersonMapper;
    }

    public void setComPersonMapper(ComPersonMapper comPersonMapper) {
        this.comPersonMapper = comPersonMapper;
    }
}
