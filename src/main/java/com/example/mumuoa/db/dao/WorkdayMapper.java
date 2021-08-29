package com.example.mumuoa.db.dao;

import com.example.mumuoa.db.pojo.Workday;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WorkdayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Workday record);

    int insertSelective(Workday record);

    Workday selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Workday record);

    int updateByPrimaryKey(Workday record);
}