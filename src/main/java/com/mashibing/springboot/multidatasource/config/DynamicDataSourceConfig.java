package com.mashibing.springboot.multidatasource.config;

import com.mashibing.springboot.multidatasource.constant.DataSourceType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * description  DynamicDataSourceConfig <BR>
 * <p>
 * author: zhao.song
 * date: created in 23:14  2022/1/13
 * company: TRS信息技术有限公司
 * version 1.0
 */

@Configuration
public class DynamicDataSourceConfig {

    @Bean
    public DataSource masterDataSource(@Qualifier("masterDBProperties") DataSourceProperties masterProperties) {
        return masterProperties.initializeDataSourceBuilder().build();
    }


    @Bean
    public DataSource slaveDataSource(@Qualifier("slaveDBProperties") DataSourceProperties slaveProperties) {
        return slaveProperties.initializeDataSourceBuilder().build();
    }

    @Bean("dynamicDataSource")
    @Primary
    public DynamicDataSource dataSource(DataSource masterDataSource, DataSource slaveDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.MASTER, masterDataSource);
        targetDataSources.put(DataSourceType.SLAVE, slaveDataSource);
        return new DynamicDataSource(masterDataSource, targetDataSources);
    }

}
