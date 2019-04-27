package com.tgw.account.receipts.dao;

import com.tgw.account.receipts.model.Receipts;
import com.tgw.basic.framework.dao.BaseModelMapper;

import java.util.List;
import java.util.Map;

public interface ReceiptsMapper extends BaseModelMapper<Receipts> {
    /**
     * 收入折线统计数据
     * @param receipts
     * @return
     */
    List<Map<String,Object>> queryStatisticsReceiptsData(Receipts receipts );

    /**
     * 根据条件求总和
     * @param receipts
     * @return
     */
    Double statisticSum(Receipts receipts);
}