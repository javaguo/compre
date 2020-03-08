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
    List<Map<String,Object>> queryReceiptsTypeMap(@Param("fkUserId")String fkUserId)  throws PlatformException;


    /**
     * 统计某个用户某个类型节点的相同序号的数量
     * @param fkUserId
     * @param fkParentId
     * @param orderNum
     * @return
     */
    int countByOrderNum( @Param("fkUserId")int fkUserId,@Param("fkParentId")int fkParentId,@Param("orderNum")int orderNum );

    /**
     * 查询子收入类型数量
     * @param idList
     * @return
     */
    int queryChildRecord(  @Param("idList") List<String> idList ,@Param("fkUserId")int fkUserId );

    /**
     * 查询支出记录是否有指定收入类型的记录
     * @param idList
     * @param fkUserId
     * @return
     */
    int queryReceiptsRecord(  @Param("idList") List<String> idList ,@Param("fkUserId")int fkUserId );

    /**
     * 查询要统计的收入类型
     * @param fkUserId
     * @param idList
     * @return
     */
    List<ReceiptsType> queryStatisticsReceiptsType(  @Param("fkUserId")int fkUserId,@Param("idList") List<String> idList  );
}