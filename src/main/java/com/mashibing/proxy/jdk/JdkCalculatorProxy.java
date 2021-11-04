package com.mashibing.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * description  JdkCalculatorProxy <BR>
 * <p>
 * author: zhao.song
 * date: created in 9:53  2021/10/19
 * company: TRS信息技术有限公司
 * version 1.0
 */
public class JdkCalculatorProxy {

    public static Calculator getProxy(Calculator instance) {

//        return (Calculator) Proxy.newProxyInstance(Calculator.class.getClassLoader(), Calculator.class.getInterfaces(), new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                Object result = method.invoke(instance, args);
//                return result;
//            }
//        });
        return (Calculator) Proxy.newProxyInstance(instance.getClass().getClassLoader(), instance.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                Object result = method.invoke(instance, args);
                return result;
            }
        });
    }
}
