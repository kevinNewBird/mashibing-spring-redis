package com.mashibing.springboot.multidatasource.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * description  DynamicDataSource <BR>
 * <p>
 * author: zhao.song
 * date: created in 23:12  2022/1/13
 * company: TRS信息技术有限公司
 * version 1.0
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        // afterPropertiesSet()方法调用时用来将targetDataSources的属性写入resolvedDataSources中的
        super.afterPropertiesSet();
    }

    /**
     * description   获取数据源  <BR>
     *
     * @param :
     * @return {@link Object}
     * @author zhao.song  2022/1/13  23:13
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSource();
    }
}
