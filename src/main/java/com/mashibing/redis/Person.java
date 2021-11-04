package com.mashibing.redis;

import java.time.Period;

/**
 * description  Person <BR>
 * <p>
 * author: zhao.song
 * date: created in 18:31  2021/10/13
 * company: TRS信息技术有限公司
 * version 1.0
 */
public class Person implements Cloneable{

    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
