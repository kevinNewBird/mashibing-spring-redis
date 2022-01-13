package com.mashibing.weibo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * description  新浪微博账号选择器 <BR>
 * 用于处理接口限制频次的注解，选取最优的账户进行接口的请求（切面注解类）
 * <p>
 * author: zhao.song
 * date: created in 10:36  2022/1/11
 * company: TRS信息技术有限公司
 * version 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SequenceLimitSelectors {

    /**
     * description  使用容器里的校验器名 <BR>
     *
     * @param :
     * @return {@link String[]}
     * @author zhao.song  2022/1/11  10:39
     */
    String validator();

    /**
     * description   多限制选择器配置  <BR>
     *
     * @param :
     * @return {@link SequenceLimitSelector[]}
     * @author zhao.song  2022/1/11  10:52
     */
    SequenceLimitSelector[] value();

}
