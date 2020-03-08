package com.tgw.omnipotent.comEventsInterval.dao;

import com.tgw.basic.framework.dao.BaseModelMapper;
import com.tgw.omnipotent.comEventsInterval.model.ComEventsInterval;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ComEventsIntervalMapper extends BaseModelMapper<ComEventsInterval> {
    /**
     * 加载事件频率类型
     * @return
     */
    public abstract List<Map<String,Object>> loadEventIntervalComboBoxMap( @Param("fkUserId")int fkUserId );
}