package com.tgw.account.util.type;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by zjg on 2019-4-13.
 */
public class TypeUtil {

    /**
     * 移除没有子节点的节点
     * @param typeList
     * @return
     */
    public static List<Map<String,Object>> removeNoChildNode(List<Map<String,Object>> typeList){
        if (CollectionUtils.isEmpty(typeList)){
            return typeList;
        }
        String rootVal = "-1";
        List<String> firstLevelIdList = new ArrayList<String>();// 有子节点的第一层结点id集合
        for (Map<String,Object> map:typeList){
            if (map.get("fk_parent_id")!=null && !rootVal.equals(map.get("fk_parent_id").toString())){// fk_parent_id不是-1，那么此节点就是第二层节点
                firstLevelIdList.add(map.get("fk_parent_id").toString());// 把第二层节点的fk_parent_id取出来就是第一层结点的id值
            }
        }

        Iterator<Map<String,Object>> ite = typeList.iterator();
        while (ite.hasNext()){
            Map<String,Object> map = ite.next();
            /**
             * 只对第一层节点的数据进行过滤处理，fk_parent_id值为-1代表第一层节点。
             * firstLevelIdList中不包含当前节点，说明当前第一层节点没有子节点。
             */
            if (map.get("id")!=null && map.get("fk_parent_id")!=null
                    && rootVal.equals(map.get("fk_parent_id").toString())
                    && !firstLevelIdList.contains(map.get("id").toString())){
                ite.remove();
            }
        }
        return typeList;
    }

    /**
     * 移除每个一级节点的子节点
     * @param typeList
     * @return
     */
    public static List<Map<String,Object>> removeChildLevel1(List<Map<String,Object>> typeList){
        if (CollectionUtils.isEmpty(typeList)){
            return typeList;
        }
        String rootVal = "-1";

        Iterator<Map<String,Object>> ite = typeList.iterator();
        while (ite.hasNext()){
            Map<String,Object> map = ite.next();
            if (map.get("fk_parent_id")!=null && !rootVal.equals(map.get("fk_parent_id").toString())){// fk_parent_id不是-1，那么此节点就是第二层节点
                ite.remove();// 把第二级层节点都移除掉
            }
        }
        return typeList;
    }
}
