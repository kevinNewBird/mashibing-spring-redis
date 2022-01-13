package com.mashibing.weibo.controller;

import com.mashibing.weibo.vo.WeiboErrorResponseResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;

/**
 * description  WeiboCommonController <BR>
 * <p>
 * author: zhao.song
 * date: created in 12:39  2022/1/13
 * company: TRS信息技术有限公司
 * version 1.0
 */
@RestController
public class WeiboCommonController {


    @RequestMapping("/common/internal")
    public WeiboErrorResponseResult commonInternal(ServletRequest request) {
        return WeiboErrorResponseResult.builder().error_code(200)
                .error_message("success！").request("/common/internal").build();
    }
}
