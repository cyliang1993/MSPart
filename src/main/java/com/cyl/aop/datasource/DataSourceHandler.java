package com.cyl.aop.datasource;

/**
 * 数据源的Handler类
 * ClassName: DataSourceHandler  ;
 * date: 2017年1月5日 上午10:39:28  ;
 * @author cyliang
 * @version 
 * @since JDK 1.8
 */
 
public class DataSourceHandler {

    /**
     * 数据源名称线程池 
     */
    public static final ThreadLocal<String> holder = new ThreadLocal<String>();

    /**
     * 
     * putDataSource:(在项目启动的时候将配置的读、写数据源加到holder中) ;
     * @author cyliang
     * @param datasource
     * @since JDK 1.8
     */
    public static void putDataSource(String datasource) {
        holder.set(datasource);
    }

    /**
     * 
     * getDataSource:(从holer中获取数据源字符串) ;
     * @author cyliang
     * @return
     * @since JDK 1.8
     */
    public static String getDataSource() {
        return holder.get();
    }
}
