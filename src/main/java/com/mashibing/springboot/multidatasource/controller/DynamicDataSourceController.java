package com.mashibing.springboot.multidatasource.controller;

import com.mashibing.springboot.multidatasource.annotation.DataSource;
import com.mashibing.springboot.multidatasource.constant.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * description  DynamicDataSourceController <BR>
 * <p>
 * author: zhao.song
 * date: created in 23:37  2022/1/13
 * company: TRS信息技术有限公司
 * version 1.0
 */
@RestController
@RequestMapping("/dynamic")
public class DynamicDataSourceController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/findAll")
    @DataSource
    public List<Map<String, Object>> findAll() {
        return jdbcTemplate.queryForList("select * from glass");
    }

    @RequestMapping("/findAll2")
    public List<Map<String, Object>> findAll2() {
        return jdbcTemplate.queryForList("select * from glass");
    }

    @RequestMapping("/findAll3")
    @DataSource(type = DataSourceType.SLAVE)
    public List<Map<String, Object>> findAll3() {
        return jdbcTemplate.queryForList("select * from glass");
    }
}
