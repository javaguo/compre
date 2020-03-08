package com.tgw.omnipotent.comPerson.dao;

import com.tgw.basic.common.exception.PlatformException;
import com.tgw.basic.framework.dao.BaseModelMapper;
import com.tgw.omnipotent.comPerson.model.ComPerson;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ComPersonMapper extends BaseModelMapper<ComPerson> {
    /**
     * 加载通讯录人员
     * @param fkUserId
     * @return
     * @throws PlatformException
     */
    public abstract List<Map<String,Object>> loadComPersonComboBoxMap(@Param("fkUserId") int fkUserId)  throws PlatformException;
}