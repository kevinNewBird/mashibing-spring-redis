package com.mashibing.qgh.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * description  PowerNation3ndConfig <BR>
 * <p>
 * author: zhao.song
 * date: created in 15:02  2021/12/30
 * company: TRS信息技术有限公司
 * version 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "qiangguohao1")
@Data
public class PowerNation2ndAccessConfig extends Abstract3ndAccessConfig implements EnvironmentAware {

    private Environment env;

    /**
     * Set the {@code Environment} that this component runs in.
     *
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }
}
