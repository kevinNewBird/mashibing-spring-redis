package com.mashibing.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * description  自定义的template <BR>
 * <p>
 * author: zhao.song
 * date: created in 22:35  2021/10/13
 * company: TRS信息技术有限公司
 * version 1.0
 */
@Configuration
public class MyTemplate {

    @Bean
    public StringRedisTemplate stringRestTemplate(RedisConnectionFactory fc) {
        StringRedisTemplate tp = new StringRedisTemplate(fc);
        tp.setHashValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        return tp;
    }
}
