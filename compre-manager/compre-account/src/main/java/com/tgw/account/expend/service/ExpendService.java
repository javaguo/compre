package com.tgw.account.expend.service;


import com.tgw.account.expend.model.Expend;
import com.tgw.basic.common.exception.PlatformException;
import com.tgw.basic.framework.service.BaseService;

import java.util.List;
import java.util.Map;


public interface ExpendService extends BaseService {
    /**
     * 支出折线统计数据
     * @param expend
     * @return
     */
    List<Map<String,Object>> queryStatisticsExpendData(Expend expend );

    /**
     * 保存更新支出数据时进行校验
     * @param expend
     * @throws PlatformException
     */
    void checkExpendBeforSaveOrUpdate(Expend expend) throws PlatformException;
}
