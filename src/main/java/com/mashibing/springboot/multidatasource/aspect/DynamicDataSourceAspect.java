package com.mashibing.springboot.multidatasource.aspect;

import com.mashibing.springboot.multidatasource.annotation.DataSource;
import com.mashibing.springboot.multidatasource.config.DataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * description  DynamicDataSourceAspect <BR>
 * <p>
 * author: zhao.song
 * date: created in 23:28  2022/1/13
 * company: TRS信息技术有限公司
 * version 1.0
 */
@EnableAspectJAutoProxy
@Aspect
@Component
public class DynamicDataSourceAspect {

    @Pointcut("@annotation(com.mashibing.springboot.multidatasource.annotation.DataSource)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        if (methodSignature.getMethod().isAnnotationPresent(DataSource.class)) {
            DataSource dsAnn = methodSignature.getMethod().getAnnotation(DataSource.class);
            DataSourceContextHolder.setDataSource(dsAnn.type());
        }
        try {
            return joinPoint.proceed(joinPoint.getArgs());
        } finally {
            DataSourceContextHolder.clear();
        }
    }
}
