package com.mashibing.weibo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * description  MethodParamHolder <BR>
 * <p>
 * author: zhao.song
 * date: created in 12:56  2022/1/13
 * company: TRS信息技术有限公司
 * version 1.0
 */
@Data
@Accessors(chain = true)
public class MethodParamHolder<Param> {
    private Param param;

    private int index;
}
