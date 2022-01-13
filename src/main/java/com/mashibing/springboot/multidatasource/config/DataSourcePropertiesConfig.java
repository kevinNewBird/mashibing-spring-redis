package com.mashibing.springboot.multidatasource.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description  DatasourcePropertiesConfig <BR>
 * <p>
 * author: zhao.song
 * date: created in 23:11  2022/1/13
 * company: TRS信息技术有限公司
 * version 1.0
 */
@Configuration
public class DataSourcePropertiesConfig {

    @ConfigurationProperties(prefix = "spring.datasource.master")
    @Bean(name = "masterDBProperties")
    public DataSourceProperties masterDBproperties() {
        return new DataSourceProperties();
    }


    @ConfigurationProperties(prefix = "spring.datasource.slave")
    @Bean(name = "slaveDBProperties")
    public DataSourceProperties slaveDBproperties() {
        return new DataSourceProperties();
    }
}
