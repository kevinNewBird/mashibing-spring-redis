package com.mashibing.springboot.base.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * description  spring boot整合servlet
 *    并且需要在启动类上添加注解@ServletComponentScan，同时将当前类装载
 * <BR>
 * <p>
 * author: zhao.song
 * date: created in 9:54  2022/1/12
 * company: TRS信息技术有限公司
 * version 1.0
 */
@WebFilter(filterName = "myFilter",urlPatterns="/*")
//@Component
public class MyFilter implements Filter {

    public MyFilter() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("spring boot整合filter");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
