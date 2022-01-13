package com.mashibing.springboot.multidatasource.config;

import com.mashibing.springboot.multidatasource.constant.DataSourceType;

import javax.sql.DataSource;

/**
 * description  DataSourceContextHolder <BR>
 * <p>
 * author: zhao.song
 * date: created in 23:20  2022/1/13
 * company: TRS信息技术有限公司
 * version 1.0
 */
public class DataSourceContextHolder {


    private static final ThreadLocal<DataSourceType> CONTEXT_HOLDER = new ThreadLocal<>();


    public static void setDataSource(DataSourceType type) {
        CONTEXT_HOLDER.set(type);
    }

    public static DataSourceType getDataSource() {
        return CONTEXT_HOLDER.get();
    }

    public static void clear() {
        CONTEXT_HOLDER.remove();
    }
}
