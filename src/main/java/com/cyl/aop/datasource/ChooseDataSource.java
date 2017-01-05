package com.cyl.aop.datasource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 获取数据源，用于动态切换数据源
 * ClassName: ChooseDataSource  ;
 * date: 2017年1月5日 上午10:36:53  ;
 * @author cyliang
 * @version 
 * @since JDK 1.8
 */

    public class ChooseDataSource extends AbstractRoutingDataSource {
 

    public static Map<String, List<String>> METHOD_TYPE_MAP = new HashMap<String, List<String>>();
    /**
     * 
     * TODO 简单描述该方法的实现功能（实现父类中的抽象方法，获取数据源名称）.
     * @see org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource#determineCurrentLookupKey()
     */
    protected Object determineCurrentLookupKey() {
        return DataSourceHandler.getDataSource();
    }

    // 设置方法名前缀对应的数据源
    public void setMethodType(Map<String, String> map) {
        for (String key : map.keySet()) {
            List<String> v = new ArrayList<String>();
            String[] types = map.get(key).split(",");
            for (String type : types) {
                if (StringUtils.isNotBlank(type)) {
                    v.add(type);
                }
            }
            METHOD_TYPE_MAP.put(key, v);
        }
    }
}
