package com.mashibing.springboot.base.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@WebServlet(name="myServlet",urlPatterns="/srv")
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("spring boot整合servlet");
        super.doGet(req, resp);
    }
}
