package com.mashibing.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.Jackson2HashMapper;
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
    ObjectMapper objectMapper;


    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;


    public void testRedis() throws CloneNotSupportedException {

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

        redisTemplate.opsForHash().putAll("sean01", mapper.toHash(person));
        System.out.println(mapper.fromHash(redisTemplate.opsForHash().entries("sean01")));


        Jackson2HashMapper jacksonMapper = new Jackson2HashMapper(objectMapper, false);

        Person p2 = (Person) person.clone();
        p2.setName("lisi");
        System.out.println(person);
        System.out.println(p2);
        redisTemplate.opsForHash().putAll("sean02", jacksonMapper.toHash(p2));
        System.out.println(jacksonMapper.fromHash(redisTemplate.opsForHash().entries("sean02")));


        //设置自定义的序列化器
        Person p3 = new Person();
        p3.setName("wangwu");
        p3.setAge(20);
        //没有指定会导致反序列化时报错(认为制定了StringRedisTemplate--MyTemplate)
//        stringRedisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        stringRedisTemplate.opsForHash().putAll("sean03", jacksonMapper.toHash(p3));
        Person sean03 = objectMapper.convertValue(stringRedisTemplate.opsForHash().entries("sean03"), Person.class);
        System.out.println(sean03.getName() + "--" + sean03.getAge());

        // 发布订阅消息

        // 接收消息

        conn.subscribe(new MessageListener() {
            @Override
            public void onMessage(Message message, byte[] pattern) {
                byte[] body = message.getBody();
                System.out.println(new String(body));
//                System.out.println(new String(pattern));
            }
        }, "sean-channel".getBytes());

        while (true) {
            // 发布消息
            stringRedisTemplate.convertAndSend("sean-channel", "hello");
        }
    }
}
