package com.tgw.account.receiptsType.service;


import com.tgw.account.receipts.model.Receipts;
import com.tgw.account.receiptsType.model.ReceiptsType;
import com.tgw.basic.common.exception.PlatformException;
import com.tgw.basic.framework.service.BaseService;

import java.util.List;
import java.util.Map;


public interface ReceiptsTypeService extends BaseService {
    /**
     * 查询收入类型树节点数据
     * @return
     * @throws PlatformException
     */
    public abstract List<Map<String,Object>> queryReceiptsTypeMap(String fkUserId)  throws PlatformException;

    /**
     * 复制系统默认的收入类型到当前用户下
     */
    public abstract void saveReceiptsTypeFromSys();

    /**
     * 删除前进行验证检查
     * @param idList
     */
    public abstract void checkBeforeDelete( List<String> idList );

    /**
     * 查询要统计的收入类型
     * @param bean
     * @return
     */
    List<ReceiptsType> queryStatisticsReceiptsType(  Receipts bean  );
}
