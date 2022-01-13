package com.mashibing.reflect;

import java.lang.reflect.Constructor;

/**
 * description  B <BR>
 * <p>
 * author: zhao.song
 * date: created in 11:24  2021/11/24
 * company: TRS信息技术有限公司
 * version 1.0
 */
public class B {


    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public B() {
    }

    public B(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "B{" +
                "id=" + id +
                '}'+hashCode();
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Constructor<B> noArgsCons = B.class.getDeclaredConstructor();
        B b = new B();
        b.setId(1);
        System.out.println(b);
        instanceLink(b);
        System.out.println(b);
    }


    private static void instanceLink(B b) {
        b = new B();
        b.setId(45);
    }
}
