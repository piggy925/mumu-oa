package com.example.mumuoa.db.dao;

import com.example.mumuoa.db.pojo.Checkin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CheckinMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Checkin record);

    int insertSelective(Checkin record);

    Checkin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Checkin record);

    int updateByPrimaryKey(Checkin record);
}