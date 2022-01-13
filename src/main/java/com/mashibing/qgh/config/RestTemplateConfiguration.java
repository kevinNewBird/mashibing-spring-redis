package com.mashibing.qgh.config;

import com.mashibing.qgh.interceptor.PowerNationRestAuthInterceptor;
import com.mashibing.qgh.interceptor.TimerHttpRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * description  RestTemplateConfiguration <BR>
 * <p>
 * author: zhao.song
 * date: created in 18:08  2021/12/29
 * company: TRS信息技术有限公司
 * version 1.0
 */
@Configuration
public class RestTemplateConfiguration {

    @Autowired
    private PowerNation3ndAccessConfig pnConfig;

    @Bean
    public RestTemplate restTemplate() {
        // https://www.jianshu.com/p/e194b0403cff
        RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));

        //设置拦截器
        restTemplate.setInterceptors(Stream.of(
                        // 强国号http请求拦截器：1.统一处理token签名
                        new PowerNationRestAuthInterceptor(pnConfig),
                        new TimerHttpRequestInterceptor()
                )
                .collect(Collectors.toList()));
        return restTemplate;
    }

}
