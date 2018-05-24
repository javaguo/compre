package com.tgw.account.expendType.service;


import com.tgw.basic.common.exception.PlatformException;
import com.tgw.basic.framework.service.BaseService;

import java.util.List;
import java.util.Map;


public interface ExpendTypeService extends BaseService {
    /**
     * 查询支出类型树节点数据
     * @return
     * @throws PlatformException
     */
    public abstract List<Map<String,Object>> queryExpendTypeTreeMap(String fkUserId)  throws PlatformException;

    /**
     * 复制系统默认的支出类型到当前用户下
     */
    public abstract void saveExpendTypeFromSys();

    /**
     * 删除前进行验证检查
     * @param idList
     */
    public abstract void checkBeforeDelete( List<String> idList );
}
