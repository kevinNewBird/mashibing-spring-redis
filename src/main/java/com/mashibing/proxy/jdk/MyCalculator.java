package com.mashibing.proxy.jdk;

/**
 * description  MyCalculator <BR>
 * <p>
 * author: zhao.song
 * date: created in 9:52  2021/10/19
 * company: TRS信息技术有限公司
 * version 1.0
 */
public class MyCalculator implements Calculator {
    @Override
    public int add(int i, int j) {
        return i + j;
    }

    @Override
    public int sub(int i, int j) {
        return i - j;
    }

    @Override
    public int multi(int i, int j) {
        return i * j;
    }

    @Override
    public int div(int i, int j) {
        return i / j;
    }
}
