package com.mashibing.weibo.constant;

import lombok.Getter;

/**
 * description  SinaSelectorPropEnum <BR>
 * <p>
 * author: zhao.song
 * date: created in 10:41  2022/1/11
 * company: TRS信息技术有限公司
 * version 1.0
 */
public enum SelectorLimitTypeEnum {
    // 新浪应用请求接口限制
    SINA_INTERFACE_LIMIT("sina")

    // 新浪ip限制
    , SINA_IP_LIMIT("sina")

    // 公司内部购买uid限制
    , SINA_UID_LIMIT("sina");


    @Getter
    private String channelType;

    SelectorLimitTypeEnum(String channelType) {
        this.channelType = channelType;
    }
}
