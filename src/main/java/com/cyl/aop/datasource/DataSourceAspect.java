package com.cyl.aop.datasource;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
/**
 * 切换数据源(不同方法调用不同数据源)
 * ClassName: DataSourceAspect  ;
 * date: 2017年1月5日 上午10:38:39  ;
 * @author cyliang
 * @version 
 * @since JDK 1.8
 */
@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DataSourceAspect {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

   
    /**
     * 
     * before:(配置前置通知,使用在方法aspect()上注册的切入点) ;
     * @author cyliang
     * @param point
     * @since JDK 1.8
     */
     
    @Before("execution(* com.cyl.dao.UserDao.*(..))")
    public void before(JoinPoint point) {
        String className = point.getTarget().getClass().getName();
        String method = point.getSignature().getName();
        logger.info("***********LOG****************："+className + "." + method + "(" + StringUtils.join(point.getArgs(), ",") + ")");
        try {
            for (String key : ChooseDataSource.METHOD_TYPE_MAP.keySet()) {
                for (String type : ChooseDataSource.METHOD_TYPE_MAP.get(key)) {
                    if (method.startsWith(type)) {
                        DataSourceHandler.putDataSource(key);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
