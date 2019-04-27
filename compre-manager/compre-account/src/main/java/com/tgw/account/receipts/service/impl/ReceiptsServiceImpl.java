package com.tgw.account.receipts.service.impl;


import com.tgw.account.receipts.dao.ReceiptsMapper;
import com.tgw.account.receipts.model.Receipts;
import com.tgw.account.receipts.service.ReceiptsService;
import com.tgw.account.receiptsType.dao.ReceiptsTypeMapper;
import com.tgw.account.receiptsType.model.ReceiptsType;
import com.tgw.basic.common.exception.PlatformException;
import com.tgw.basic.framework.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("receiptsService")
public class ReceiptsServiceImpl extends BaseServiceImpl implements ReceiptsService {
    @Resource
    private ReceiptsMapper receiptsMapper;
    @Resource
    private ReceiptsTypeMapper receiptsTypeMapper;

    @Override
    public void initMapper() {
        /**
        * 具体业务service层必须覆写此方法
        */
        if( null!=receiptsMapper ){
            super.setBaseModelMapper( this.getReceiptsMapper() );
        }
    }

    /**
     * 统计收入数据
     * @param receipts
     * @return
     */
    public List<Map<String,Object>> queryStatisticsReceiptsData(Receipts receipts ){
        return getReceiptsMapper().queryStatisticsReceiptsData( receipts );
    }

    public Double statisticSum(Receipts receipts) {
        return getReceiptsMapper().statisticSum(receipts);
    }

    public void checkReceiptsBeforSaveOrUpdate(Receipts receipts) throws PlatformException {
        ReceiptsType receiptsType = new ReceiptsType();
        receiptsType.setId( receipts.getFkReceiptsTypeId() );
        receiptsType = getReceiptsTypeMapper().selectByPrimaryKey(receiptsType);
        if( receiptsType.getFkParentId()==-1 ){
            throw new PlatformException("收入类型只能选二级收入类型！");
        }
    }
    public ReceiptsMapper getReceiptsMapper() {
        return receiptsMapper;
    }

    public void setReceiptsMapper(ReceiptsMapper receiptsMapper) {
        this.receiptsMapper = receiptsMapper;
    }

    public ReceiptsTypeMapper getReceiptsTypeMapper() {
        return receiptsTypeMapper;
    }

    public void setReceiptsTypeMapper(ReceiptsTypeMapper receiptsTypeMapper) {
        this.receiptsTypeMapper = receiptsTypeMapper;
    }
}
