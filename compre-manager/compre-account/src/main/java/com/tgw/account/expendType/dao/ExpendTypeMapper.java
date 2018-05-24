package com.tgw.account.expendType.dao;

import com.tgw.account.expendType.model.ExpendType;
import com.tgw.basic.common.exception.PlatformException;
import com.tgw.basic.framework.dao.BaseModelMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ExpendTypeMapper extends BaseModelMapper<ExpendType> {
    /**
     * 查询支出类型树节点数据
     * @return
     * @throws PlatformException
     */
    public abstract List<Map<String,Object>> queryExpendTypeTreeMap(@Param("fkUserId")String fkUserId)  throws PlatformException;

    /**
     * 查询子支出类型数量
     * @param idList
     * @return
     */
    public abstract int queryChildRecord(  @Param("idList") List<String> idList ,@Param("fkUserId")int fkUserId );

    /**
     * 查询支出记录是否有指定支出类型的记录
     * @param idList
     * @param fkUserId
     * @return
     */
    public abstract int queryExpendRecord(  @Param("idList") List<String> idList ,@Param("fkUserId")int fkUserId );
}