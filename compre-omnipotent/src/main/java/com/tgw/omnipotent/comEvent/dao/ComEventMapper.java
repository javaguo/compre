package com.tgw.omnipotent.comEvent.dao;

import com.tgw.basic.common.exception.PlatformException;
import com.tgw.basic.framework.dao.BaseModelMapper;
import com.tgw.omnipotent.comEvent.model.ComEvent;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ComEventMapper extends BaseModelMapper<ComEvent> {
    /**
     * 加载事件
     * @param fkUserId
     * @param minDate
     * @return
     * @throws PlatformException
     */
    public abstract List<Map<String,Object>> loadComEventComboBoxMap(@Param("fkUserId") int fkUserId,@Param("minDate")Date minDate)  throws PlatformException;
}