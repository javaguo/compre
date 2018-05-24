package com.tgw.account.refund.service.impl;


import com.tgw.account.refund.dao.RefundMapper;
import com.tgw.account.refund.service.RefundService;
import com.tgw.basic.framework.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("refundService")
public class RefundServiceImpl extends BaseServiceImpl implements RefundService {
    @Resource
    private RefundMapper refundMapper;

    @Override
    public void initMapper() {
        /**
        * 具体业务service层必须覆写此方法
        */
        if( null!=refundMapper ){
            super.setBaseModelMapper( this.getRefundMapper() );
        }
    }

    public RefundMapper getRefundMapper() {
        return refundMapper;
    }

    public void setRefundMapper(RefundMapper refundMapper) {
        this.refundMapper = refundMapper;
    }
}
