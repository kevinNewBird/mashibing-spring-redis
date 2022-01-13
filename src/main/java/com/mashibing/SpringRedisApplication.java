package com.mashibing;

import com.mashibing.springboot.base.servlet.MyServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ServletComponentScan(basePackages = "com.mashibing.servlet") // 整合servlet需要的启动类
// WebMvcAutoConfiguration完成静态资源的处理
public class SpringRedisApplication {

    public static void main(String[] args) throws CloneNotSupportedException {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringRedisApplication.class, args);
//        TestRedis redis = ctx.getBean(TestRedis.class);
//        redis.testRedis();
    }

    /**
     * description   自定义装载servlet(也可以不需要)  <BR>
     *
      * @param :
     * @return {@link ServletRegistrationBean< MyServlet>}
     * @author zhao.song  2022/1/12  10:02
     */
    @Bean
    public ServletRegistrationBean<MyServlet> getServletRegistrationBean() {
        // 修改访问地址： srv -> srv2
        ServletRegistrationBean<MyServlet> registrationBean
                = new ServletRegistrationBean<>(new MyServlet(),"/srv2");

        registrationBean.setLoadOnStartup(1);
        return registrationBean;
    }

}
