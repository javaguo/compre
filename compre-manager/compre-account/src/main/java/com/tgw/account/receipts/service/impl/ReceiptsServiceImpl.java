package com.tgw.account.receipts.service.impl;


import com.tgw.account.receipts.dao.ReceiptsMapper;
import com.tgw.account.receipts.service.ReceiptsService;
import com.tgw.basic.framework.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("receiptsService")
public class ReceiptsServiceImpl extends BaseServiceImpl implements ReceiptsService {
    @Resource
    private ReceiptsMapper receiptsMapper;

    @Override
    public void initMapper() {
        /**
        * 具体业务service层必须覆写此方法
        */
        if( null!=receiptsMapper ){
            super.setBaseModelMapper( this.getReceiptsMapper() );
        }
    }

    public ReceiptsMapper getReceiptsMapper() {
        return receiptsMapper;
    }

    public void setReceiptsMapper(ReceiptsMapper receiptsMapper) {
        this.receiptsMapper = receiptsMapper;
    }
}
