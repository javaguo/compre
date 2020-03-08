package com.tgw.account.lend.service.impl;


import com.tgw.account.lend.dao.LendMapper;
import com.tgw.account.lend.service.LendService;
import com.tgw.basic.framework.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("lendService")
public class LendServiceImpl extends BaseServiceImpl implements LendService {
    @Resource
    private LendMapper lendMapper;

    @Override
    public void initMapper() {
        /**
        * 具体业务service层必须覆写此方法
        */
        if( null!=lendMapper ){
            super.setBaseModelMapper( this.getLendMapper() );
        }
    }

    public LendMapper getLendMapper() {
        return lendMapper;
    }

    public void setLendMapper(LendMapper lendMapper) {
        this.lendMapper = lendMapper;
    }
}
