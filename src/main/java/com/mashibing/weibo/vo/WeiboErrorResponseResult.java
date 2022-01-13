package com.mashibing.weibo.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * description  WeiboResponseResult <BR>
 * <p>
 * author: zhao.song
 * date: created in 12:40  2022/1/13
 * company: TRS信息技术有限公司
 * version 1.0
 */
@Data
@Builder
public class WeiboErrorResponseResult implements Serializable {

    private String request;

    private int error_code;

    private String error_message;
}
