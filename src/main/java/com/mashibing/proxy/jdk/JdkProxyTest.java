package com.mashibing.proxy.jdk;

/**
 * description  JdkProxyTest <BR>
 * <p>
 * author: zhao.song
 * date: created in 10:04  2021/10/19
 * company: TRS信息技术有限公司
 * version 1.0
 */
public class JdkProxyTest {


    public static void main(String[] args) {
        Calculator calculator = JdkCalculatorProxy.getProxy(new MyCalculator());
        calculator.add(1, 1);
        System.out.println(calculator.getClass());
    }
}
