package com.mashibing.weibo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * description  SinaRequestSequenceLimitConfig <BR>
 * <p>
 * author: zhao.song
 * date: created in 13:31  2022/1/11
 * company: TRS信息技术有限公司
 * version 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "sequence", ignoreInvalidFields = true)
public class RequestSequenceLimitConfig {

    private Map<String, Map<String, List<String>>> limitConfig;

    public Map<String, Map<String, List<String>>> getLimitConfig() {
        return limitConfig;
    }

    public void setLimitConfig(Map<String, Map<String, List<String>>> limitConfig) {
        this.limitConfig = limitConfig;
    }
}
