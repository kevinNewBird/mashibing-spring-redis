package com.mashibing.springboot.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * description  模版引擎thymeleaf <BR>
 * <p>
 * author: zhao.song
 * date: created in 8:42  2022/1/13
 * company: TRS信息技术有限公司
 * version 1.0
 */
@Controller
@RequestMapping("/tf")
public class ThymeleafController {

    @RequestMapping("/hello")
    public String hello() {
        return "success";
    }

    @RequestMapping("/hello2")
    public String hello2(Model model) {
        model.addAttribute("msg", "thymeleaf引入");
        return "success3";
    }


    //     需要在配置文件中指定i18n配置路径，即spring.messages.basename
    @RequestMapping("/login")
    public String thymeleafLogin() {
        return "login";
    }
}
