package com.mashibing.reflect;

import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * description  A <BR>
 * <p>
 * author: zhao.song
 * date: created in 16:11  2021/10/15
 * company: TRS信息技术有限公司
 * version 1.0
 */
public class A {


    private int name;

    public static int age = 1;

    public static void main(String[] args) throws IOException {
        simulateGenerateDateCenterId();
        // getFields只会获取public的属性
        Field[] fields = A.class.getFields();
        for (Field field : fields) {
            int modifiers = field.getModifiers();
            // 判断属性的修饰词
            System.out.println(Modifier.isPublic(modifiers));
            System.out.println(Modifier.isFinal(modifiers));
            System.out.println(Modifier.isStatic(modifiers));
        }
        Field[] dFields = A.class.getDeclaredFields();
        System.in.read();
    }


    public static void simulateGenerateDateCenterId() {
        long id = 1l;
        byte[] mac = NetUtil.getLocalHardwareAddress();
        if (null != mac) {
            System.out.println("mac byte array's length is " + mac.length);
            System.out.println("mac byte array's inversed second bit is " + mac[mac.length - 2]);
            System.out.println("mac byte array's inversed first bit is " + mac[mac.length - 1]);
            id = ((0x000000FF & (long) mac[mac.length - 2])
                    | (0x0000FF00 & (((long) mac[mac.length - 1]) << 8))) >> 6;
            long dataCenterId = IdUtil.getDataCenterId(31L);
            long workerId = IdUtil.getWorkerId(dataCenterId, 31L);
            System.out.println("dataCenterId is " + dataCenterId);
            System.out.println("workerId is " + workerId);
        }
    }
}
