package com.mashibing.springboot.base.servlet;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * description  MyListener <BR>
 * <p>
 * author: zhao.song
 * date: created in 10:38  2022/1/12
 * company: TRS信息技术有限公司
 * version 1.0
 */
@WebListener
public class MyListener implements HttpSessionListener {
    public static int online = 0;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("创建session");
        online++;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("销毁session");
    }
}
