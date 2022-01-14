package com.mashibing.springboot.mybatis.controller;

import com.mashibing.springboot.multidatasource.annotation.DataSource;
import com.mashibing.springboot.multidatasource.constant.DataSourceType;
import com.mashibing.springboot.mybatis.dao.GlassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * description  MybatisController <BR>
 * <p>
 * author: zhao.song
 * date: created in 9:03  2022/1/14
 * company: TRS信息技术有限公司
 * version 1.0
 */
@RestController
@RequestMapping("/mybatis")
public class MybatisController {

    @Autowired
    private GlassMapper glassMapper;

    @RequestMapping("/findAll")
    public List<Map<Object, Object>> findAll() {
        return glassMapper.findAll();
    }

    @RequestMapping("/findAll2")
    @DataSource(type = DataSourceType.SLAVE)
    public List<Map<Object, Object>> findAll2() {
        return glassMapper.findAll();
    }
}
