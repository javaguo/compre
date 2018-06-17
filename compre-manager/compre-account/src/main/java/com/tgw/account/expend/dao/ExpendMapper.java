package com.tgw.account.expend.dao;

import com.tgw.account.expend.model.Expend;
import com.tgw.basic.framework.dao.BaseModelMapper;

import java.util.List;
import java.util.Map;

public interface ExpendMapper extends BaseModelMapper<Expend> {
    /**
     * 支出折线统计数据
     * @param expend
     * @return
     */
    List<Map<String,Object>> queryStatisticsExpendData(Expend expend );
}