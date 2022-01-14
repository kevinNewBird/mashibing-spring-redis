package com.mashibing.springboot.mybatis.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * description  GlassMapper <BR>
 * <p>
 * author: zhao.song
 * date: created in 8:58  2022/1/14
 * company: TRS信息技术有限公司
 * version 1.0
 */
@Mapper
@Repository
public interface GlassMapper {

    /**
     * description   查询所有  <BR>
     *
     * @param :
     * @return {@link List< Map< Object, Object>>}
     * @author zhao.song  2022/1/14  8:59
     */
    List<Map<Object, Object>> findAll();
}
