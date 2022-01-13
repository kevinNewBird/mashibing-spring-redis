package com.mashibing.config;

import com.mashibing.SpringRedisApplication;
import com.mashibing.qgh.config.PowerNation2ndAccessConfig;
import com.mashibing.qgh.config.PowerNation3ndAccessConfig;
import com.mashibing.qgh.vo.BaseVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description  RestTemplateTest <BR>
 * <p>
 * author: zhao.song
 * date: created in 14:17  2021/12/30
 * company: TRS信息技术有限公司
 * version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringRedisApplication.class})
public class RestTemplateTest {

    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    private PowerNation3ndAccessConfig config;
    @Autowired
    private PowerNation2ndAccessConfig config2;


    @Test
    public void testRestInterceptor() {
        Environment env = config2.getEnv();
        List list = config.getAc().getBean("list1", List.class);
        System.out.println(config.getSecretAccess());
        System.out.println(config2.getSecretAccess());
        Map<String, Object> params = new HashMap<>();
        params.put("user", "kevin");
        params.put("type", 1);
        params.put("name", "ssss");
        params.put("age", 99);
//        Map result = restTemplate.getForObject("https://www.baidu.com//{user}/{type}?name={name}&age={age}", Map.class, params);


        BaseVo vo = BaseVo.builder().item_type(1).accountName("power nation").build();
        Map result = restTemplate.postForObject("https://www.baidu.com//{user}/{type}?name={name}&age={age}", vo, Map.class,params);
        Assert.notNull(result, "请求结果返回为空！");
    }
}



