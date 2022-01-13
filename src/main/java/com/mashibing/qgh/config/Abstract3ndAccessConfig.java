package com.mashibing.qgh.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * description  Abstract3ndAccessConfig <BR>
 * <p>
 * author: zhao.song
 * date: created in 15:18  2021/12/30
 * company: TRS信息技术有限公司
 * version 1.0
 */
@Data
public abstract class Abstract3ndAccessConfig {

    @JsonProperty("open_api_url")
    private String openApiUrl;

    @JsonProperty("secret_key")
    private String secretKey;

    @JsonProperty("secret_access")
    private String secretAccess;
}
