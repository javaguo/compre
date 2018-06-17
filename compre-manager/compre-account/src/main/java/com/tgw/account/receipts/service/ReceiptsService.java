package com.tgw.account.receipts.service;


import com.tgw.account.receipts.model.Receipts;
import com.tgw.basic.framework.service.BaseService;

import java.util.List;
import java.util.Map;


public interface ReceiptsService extends BaseService {
    /**
     * 收入折线统计数据
     * @param receipts
     * @return
     */
    List<Map<String,Object>> queryStatisticsReceiptsData(Receipts receipts );
}
