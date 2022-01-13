package com.mashibing.springboot.multidatasource.annotation;

import com.mashibing.springboot.multidatasource.constant.DataSourceType;

import java.lang.annotation.*;

/**
 * description  DataSource <BR>
 * <p>
 * author: zhao.song
 * date: created in 23:29  2022/1/13
 * company: TRS信息技术有限公司
 * version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

    DataSourceType type() default DataSourceType.MASTER;
}
