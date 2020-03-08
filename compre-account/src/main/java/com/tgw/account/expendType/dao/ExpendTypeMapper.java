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
    List<Map<String,Object>> queryExpendTypeTreeMap(@Param("fkUserId")String fkUserId)  throws PlatformException;

    /**
     * 统计某个用户某个类型节点的相同序号的数量
     * @param fkUserId
     * @param fkParentId
     * @param orderNum
     * @return
     */
    int countByOrderNum( @Param("fkUserId")int fkUserId,@Param("fkParentId")int fkParentId,@Param("orderNum")int orderNum );

    /**
     * 查询子支出类型数量
     * @param idList
     * @return
     */
    int queryChildRecord(  @Param("idList") List<String> idList ,@Param("fkUserId")int fkUserId );

    /**
     * 查询支出记录是否有指定支出类型的记录
     * @param idList
     * @param fkUserId
     * @return
     */
    int queryExpendRecord(  @Param("idList") List<String> idList ,@Param("fkUserId")int fkUserId );

    /**
     * 查询要统计的支出类型
     * @param fkUserId
     * @param idList
     * @return
     */
    List queryStatisticsExpendType(  @Param("fkUserId")int fkUserId,@Param("idList") List<String> idList  );
}