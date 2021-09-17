package com.example.mumuoa.db.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WorkdayMapper {
    Integer searchTodayIsWorkday();
}