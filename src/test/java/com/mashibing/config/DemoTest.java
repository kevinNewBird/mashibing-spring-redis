package com.mashibing.config;

import org.junit.Test;

/**
 * description  DemoTest <BR>
 * <p>
 * author: zhao.song
 * date: created in 8:51  2022/1/11
 * company: TRS信息技术有限公司
 * version 1.0
 */
public class DemoTest {


    @Test
    public void test() {
        Demo d1 = new Demo();
        Demo d2 = new Demo();

        System.out.println(d1.objSf==d2.objSf);
        System.out.println(d1.objS==d2.objS);
        System.out.println(d1.objF==d2.objF);
        System.out.println(d1.indexGenerator.get());
        System.out.println(d2.indexGenerator.get());
        System.out.println(d1.index);
        System.out.println(d2.index);
    }


}
