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
    List<Map<String,Object>> queryReceiptsTypeMap(String fkUserId)  throws PlatformException;

    /**
     * 复制系统默认的收入类型到当前用户下
     */
    void saveReceiptsTypeFromSys();

    /**
     * 保存收入类型前做相应处理
     * @param bean
     * @throws PlatformException
     */
    void beforeSaveBean(ReceiptsType bean) throws PlatformException;

    /**
     * 更新收入类型前做相应处理
     * @param bean
     * @throws PlatformException
     */
    void beforeUpdateBean(ReceiptsType bean) throws PlatformException;

    /**
     * 删除前进行验证检查
     * @param idList
     */
    void checkBeforeDelete( List<String> idList );

    /**
     * 查询要统计的收入类型
     * @param bean
     * @return
     */
    List<ReceiptsType> queryStatisticsReceiptsType(  Receipts bean  );
}
