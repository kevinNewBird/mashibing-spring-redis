package com.mashibing.springboot.base.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * description  MyWebMvcConfig <BR>
 * <p>
 * author: zhao.song
 * date: created in 8:39  2022/1/13
 * company: TRS信息技术有限公司
 * version 1.0
 */
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 手动添加访问路径的视图
        // 必须引入模板引擎thymeleaf相关依赖，且视图静态文件默认放在templates目录下
        registry.addViewController("/msb").setViewName("success2");
        // 切换到指定的视图上templates,或者将login.html中的切换的login.html-> login
        registry.addViewController("/login.html").setViewName("login");

    }

    /**
     * description   可以看到通过浏览器的切换语言已经能够实现，想要通过超链接实现的话  <BR>
     * 并且需要完成视图的设置
     *
     * @param :
     * @return {@link LocaleResolver}
     * @author zhao.song  2022/1/13  16:17
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new NativeLocaleResolver();
    }

    protected static class NativeLocaleResolver implements LocaleResolver {

        @Override
        public Locale resolveLocale(HttpServletRequest request) {
            String language = request.getParameter("language");
            Locale locale = Locale.getDefault();
            if (!StringUtils.isEmpty(language)) {
                String[] split = language.split("_");
                locale = new Locale(split[0], split[1]);
            }
            return locale;
        }

        @Override
        public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

        }
    }
}
