package com.tgw.account.receiptsType.dao;

import com.tgw.account.receiptsType.model.ReceiptsType;
import com.tgw.basic.common.exception.PlatformException;
import com.tgw.basic.framework.dao.BaseModelMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ReceiptsTypeMapper extends BaseModelMapper<ReceiptsType> {
    /**
     * 查询收入类型树节点数据
     * @return
     * @throws PlatformException
     */
    public abstract List<Map<String,Object>> queryReceiptsTypeMap(@Param("fkUserId")String fkUserId)  throws PlatformException;

    /**
     * 查询子收入类型数量
     * @param idList
     * @return
     */
    public abstract int queryChildRecord(  @Param("idList") List<String> idList ,@Param("fkUserId")int fkUserId );

    /**
     * 查询支出记录是否有指定收入类型的记录
     * @param idList
     * @param fkUserId
     * @return
     */
    public abstract int queryReceiptsRecord(  @Param("idList") List<String> idList ,@Param("fkUserId")int fkUserId );
}