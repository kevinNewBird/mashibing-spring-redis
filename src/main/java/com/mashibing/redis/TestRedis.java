package com.mashibing.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.ObjectHashMapper;
import org.springframework.stereotype.Component;

/**
 * description  TestRedis <BR>
 * <p>
 * author: zhao.song
 * date: created in 17:56  2021/10/13
 * company: TRS信息技术有限公司
 * version 1.0
 */
@Component
public class TestRedis {
    HashMapper<Object, byte[], byte[]> mapper = new ObjectHashMapper();

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;


    public void testRedis() {

        // high-level
        redisTemplate.opsForValue().setIfAbsent("hello", "china");
        System.out.println(redisTemplate.opsForValue().get("hello"));
        stringRedisTemplate.opsForValue().set("hello01", "china01");
        System.out.println(stringRedisTemplate.opsForValue().get("hello01"));

        // low-level
        RedisConnection conn = redisTemplate.getConnectionFactory().getConnection();
        conn.setNX("hello02".getBytes(), "mashibing".getBytes());
        System.out.println(new String(conn.get("hello02".getBytes())));


        Person person = new Person();
        person.setName("zhangsan");
        person.setAge(16);

        redisTemplate.opsForHash().putAll("sean01",mapper.toHash(person));
        System.out.println(mapper.fromHash(redisTemplate.opsForHash().entries("sean01")));


    }
}
