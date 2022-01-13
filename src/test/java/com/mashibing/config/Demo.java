package com.mashibing.config;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * description  Demo <BR>
 * <p>
 * author: zhao.song
 * date: created in 8:53  2022/1/11
 * company: TRS信息技术有限公司
 * version 1.0
 */
public class Demo {

    public static final Object objSf = new Object();

    public final Object objF = new Object();

    public static Object objS = new Object();

    public static final AtomicInteger indexGenerator = new AtomicInteger(0);

    public final int index = indexGenerator.getAndIncrement();

}
