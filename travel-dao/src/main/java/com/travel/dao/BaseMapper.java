package com.travel.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenzp
 * @email 84797939@qq.com
 * @@version 2018-03-05
 */
public interface BaseMapper<P, E> {
    int countByExample(E example);

    int deleteByExample(E example);

    int deleteByPrimaryKey(String id);

    int insert(P record);

    int insertSelective(P record);

    List<P> selectByExample(E example);

    P selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") P record, @Param("example") E example);

    int updateByExample(@Param("record") P record, @Param("example") E example);

    int updateByPrimaryKeySelective(P record);

    int updateByPrimaryKey(P record);
}
