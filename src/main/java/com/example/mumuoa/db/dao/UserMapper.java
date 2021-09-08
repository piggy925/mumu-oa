package com.example.mumuoa.db.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface UserMapper {
    boolean haveRootUser();

    int insert(HashMap param);

    Integer searchIdByOpenId(String openId);
}