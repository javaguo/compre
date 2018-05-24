package com.tgw.account.receiptsTypeSys.service;


import com.tgw.basic.common.exception.PlatformException;
import com.tgw.basic.framework.service.BaseService;

import java.util.List;
import java.util.Map;


public interface ReceiptsTypeSysService extends BaseService {
    /**
     * 查询收入类型树节点数据
     * @return
     * @throws PlatformException
     */
    public abstract List<Map<String,Object>> queryReceiptsTypeSysTreeMap()  throws PlatformException;

    /**
     * 删除前进行验证检查
     * @param idList
     */
    public abstract void checkBeforeDelete( List<String> idList );
}
