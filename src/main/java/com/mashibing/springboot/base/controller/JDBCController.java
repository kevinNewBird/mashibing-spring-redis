package com.mashibing.springboot.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * description  JDBCController <BR>
 * <p>
 * author: zhao.song
 * date: created in 17:06  2022/1/13
 * company: TRS信息技术有限公司
 * version 1.0
 */
//@RestController
//@RequestMapping("/db")
public class JDBCController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/findAll")
    public List<Map<String, Object>> findAll() {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from glass");
        return maps;
    }

    @RequestMapping("/insertOne")
    public String insertOne() {
        int insertNum = jdbcTemplate.update("insert into glass(id,name,price) VALUES(2,'大床',222)");
        System.out.println("插入数量：" + insertNum);
        return "update_success";
    }
}
