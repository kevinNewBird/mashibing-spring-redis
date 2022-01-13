package com.mashibing.springboot;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringBootControllerTest {


    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() {
    }

    @Test
    public void test() {
        System.out.println(dataSource.getClass());

        // 查看配置是否生效
        DruidDataSource druidDataSource = (DruidDataSource) dataSource;

        System.out.println("initialSize:" + druidDataSource.getInitialSize());
        System.out.println("minIdle:" + druidDataSource.getMinIdle());
        System.out.println("maxActive:" + druidDataSource.getMaxActive());


    }
}
