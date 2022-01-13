package com.mashibing.weibo.annotation;

import com.mashibing.weibo.constant.SelectorLimitTypeEnum;

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
@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SequenceLimitSelector {

    /**
     * description     需要启用的限制规则 <BR>
     *
     * @param :
     * @return {@link String[]}
     * @author zhao.song  2022/1/11  10:39
     */
    SelectorLimitTypeEnum limitType();


    /**
     * description   需做池子处理分配的鉴名参数名  <BR>
     *
     * @param :
     * @return {@link String}
     * @author zhao.song  2022/1/12  13:30
     */
    String[] name();


    /**
     * description   需做池子处理分配的鉴名参数类型  <BR>
     *
     * @param :
     * @return {@link Class}
     * @author zhao.song  2022/1/12  13:30
     */
    Class<?>[] type();

    /**
     * description   需做池子处理分配的替换参数名称  <BR>
     *
     * @param :
     * @return {@link String[]}
     * @author zhao.song  2022/1/12  16:13
     */
    String[] replaceName();

    /**
     * description   需做池子处理分配的替换参数类型  <BR>
     *
     * @param :
     * @return {@link String[]}
     * @author zhao.song  2022/1/12  16:13
     */
    Class<?>[] replaceType();

    /**
     * description   规则处理器需要获取的Uri参数名(request参数而言是不需要的)  <BR>
     *
     * @param :
     * @return {@link String[]}
     * @author zhao.song  2022/1/12  15:15
     */
    String requiredUriName() default "";

    /**
     * description   规则处理器需要获取Uid对应的参数名  <BR>
     *
     * @param :
     * @return {@link String}
     * @author zhao.song  2022/1/12  15:17
     */
    String requiredUidName() default "uid";

    /**
     * description   规则处理器  <BR>
     * 因为对于处理的参数，是无法被确定的。需要显示指定自己的规则转换器
     * ，将其转为可以被处理的并完成真正的处理逻辑
     *
     * @param :
     * @return {@link String}
     * @author zhao.song  2022/1/12  13:49
     */
    String ruleHandler();

    /**
     * description   后缀配置值，同SinaRequestSequenceLimitConfig配置类以及limitType配合使用  <BR>
     * sequence:
     * limitConfig:
     * sina:
     * [configPostfix]:
     * - a
     * - b
     * - c
     *
     * @param :
     * @return {@link String[]}
     * @author zhao.song  2022/1/11  11:09
     */
    String[] configPostfix();

}
