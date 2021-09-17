package com.example.mumuoa.service;

import com.example.mumuoa.db.pojo.User;

import java.util.Set;

public interface UserService {
    int registerUser(String registerCode, String code, String nickname, String photo);

    Set<String> searchUserPermissions(Integer userId);

    Integer login(String code);

    User searchUserById(Integer userId);
}