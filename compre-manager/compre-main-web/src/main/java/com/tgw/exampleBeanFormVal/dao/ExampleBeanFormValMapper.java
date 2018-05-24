package com.tgw.exampleBeanFormVal.dao;


import com.tgw.basic.framework.dao.BaseModelMapper;
import com.tgw.exampleBeanFormVal.model.ExampleBeanFormVal;

import java.util.List;
import java.util.Map;

/**
 * Created by zhaojg on 2017/08/05.
 */
public interface ExampleBeanFormValMapper extends BaseModelMapper<ExampleBeanFormVal> {

    /**
     * 查询行政区划树节点数据接口
     * @return
     */
    public abstract List<Map<String,Object>> queryDistrictTreeMap();


}
