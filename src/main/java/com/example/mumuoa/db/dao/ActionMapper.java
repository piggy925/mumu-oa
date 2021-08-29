package com.example.mumuoa.db.dao;

import com.example.mumuoa.db.pojo.Action;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Action record);

    int insertSelective(Action record);

    Action selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Action record);

    int updateByPrimaryKey(Action record);
}