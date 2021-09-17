package com.example.mumuoa.db.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface CheckinMapper {
    Integer haveCheckin(HashMap param);
}