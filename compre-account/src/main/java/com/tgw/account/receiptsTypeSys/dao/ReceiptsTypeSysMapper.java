package com.tgw.account.receiptsTypeSys.dao;

import com.tgw.account.receiptsTypeSys.model.ReceiptsTypeSys;
import com.tgw.basic.common.exception.PlatformException;
import com.tgw.basic.framework.dao.BaseModelMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ReceiptsTypeSysMapper extends BaseModelMapper<ReceiptsTypeSys> {
    /**
     * 查询收入类型树节点数据
     * @return
     * @throws PlatformException
     */
    public abstract List<Map<String,Object>> queryReceiptsTypeSysTreeMap()  throws PlatformException;

    /**
     * 查询子收入类型的数量
     * @param idList
     * @return
     */
    public abstract int queryChildRecord(  @Param("idList") List<String> idList  );
}