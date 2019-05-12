package com.tgw.account.expendType.service;


import com.tgw.account.expend.model.Expend;
import com.tgw.account.expendType.model.ExpendType;
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
    List<Map<String,Object>> queryExpendTypeTreeMap(String fkUserId)  throws PlatformException;

    /**
     * 复制系统默认的支出类型到当前用户下
     */
    void saveExpendTypeFromSys();

    /**
     * 保存支出类型前做相应处理
     * @param bean
     * @throws PlatformException
     */
    void beforeSaveBean(ExpendType bean) throws PlatformException;

    /**
     * 更新支出类型前做相应处理
     * @param bean
     * @throws PlatformException
     */
    void beforeUpdateBean(ExpendType bean  ) throws PlatformException;

    /**
     * 删除前进行验证检查
     * @param idList
     */
    void checkBeforeDelete( List<String> idList );

    /**
     * 查询要统计的支出类型
     * @param bean
     * @return
     */
    List<ExpendType> queryStatisticsExpendType(Expend bean  );
}
