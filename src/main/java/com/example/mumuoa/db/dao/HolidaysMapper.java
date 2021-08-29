package com.example.mumuoa.db.dao;

import com.example.mumuoa.db.pojo.Holidays;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HolidaysMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Holidays record);

    int insertSelective(Holidays record);

    Holidays selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Holidays record);

    int updateByPrimaryKey(Holidays record);
}