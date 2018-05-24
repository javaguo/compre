package com.tgw.account.expendTypeSys.dao;

import com.tgw.account.expendTypeSys.model.ExpendTypeSys;
import com.tgw.basic.common.exception.PlatformException;
import com.tgw.basic.framework.dao.BaseModelMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ExpendTypeSysMapper extends BaseModelMapper<ExpendTypeSys> {
    /**
     * 查询支出类型树节点数据
     * @return
     * @throws PlatformException
     */
    public abstract List<Map<String,Object>> queryExpendTypeSysTreeMap()  throws PlatformException;

    /**
     * 查询子支出类型数量
     * @param idList
     * @return
     */
    public abstract int queryChildRecord(  @Param("idList") List<String> idList  );

    /**
     * 根据父ID加载所有子类型
     * @param parentId
     * @return
     */
    /*public abstract List<ExpendTypeSys> loadExpendTypeSysChildByPId(@Param("parentId") int parentId);*/
}