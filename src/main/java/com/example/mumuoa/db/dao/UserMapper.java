package com.example.mumuoa.db.dao;

import com.example.mumuoa.db.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.Set;

@Mapper
public interface UserMapper {
    boolean haveRootUser();

    int insert(HashMap param);

    Integer searchIdByOpenId(String openId);

    Set<String> searchUserPermissions(Integer userId);

    User searchById(Integer userId);

}