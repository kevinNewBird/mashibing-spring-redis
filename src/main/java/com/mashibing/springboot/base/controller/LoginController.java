package com.mashibing.springboot.base.controller;

import com.mashibing.springboot.base.servlet.MyListener;
import com.mashibing.weibo.constant.SelectorLimitTypeEnum;
import com.mashibing.weibo.annotation.SequenceLimitSelector;
import com.mashibing.weibo.annotation.SequenceLimitSelectors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

/**
 * description  LoginController <BR>
 * <p>
 * author: zhao.song
 * date: created in 10:43  2022/1/12
 * company: TRS信息技术有限公司
 * version 1.0
 */
@RestController
public class LoginController {

    @RequestMapping("/login")
    public String login(HttpSession session) {
        session.setAttribute("name", "zhangsan");
        return "success";
    }

    @RequestMapping("/online")
    public String online() {
        return "当前在线人数：" + MyListener.online;
    }

    @RequestMapping("/test")
    @SequenceLimitSelectors(validator = "sinaRequestValidator", value = {
            @SequenceLimitSelector(limitType = SelectorLimitTypeEnum.SINA_UID_LIMIT, name = "request", requiredUidName = "uids"
                    , type = ServletRequest.class, replaceName = "request", replaceType = ServletRequest.class
                    , ruleHandler = "requestToStringHandler", configPostfix = {"uidsRuleUrls"}),
    })
    public String test(ServletRequest request, String name, int value) {
        return "success";
    }



    @RequestMapping("/test2")
    @SequenceLimitSelectors(validator = "sinaRequestValidator", value = {
            @SequenceLimitSelector(limitType = SelectorLimitTypeEnum.SINA_UID_LIMIT, name = "request", requiredUidName = "uids"
                    , type = ServletRequest.class, replaceName = "request", replaceType = ServletRequest.class
                    , ruleHandler = "requestToStringHandler", configPostfix = {"uidsRuleUrls"}),
    })
    public String test2(ServletRequest request) {
        return "success";
    }

}
