package com.mashibing.qgh.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * description  BaseVo <BR>
 * <p>
 * author: zhao.song
 * date: created in 14:54  2021/12/30
 * company: TRS信息技术有限公司
 * version 1.0
 */
@Data
@Builder
public class BaseVo implements Serializable {

    private Integer item_type;

    private String accountName;

}
