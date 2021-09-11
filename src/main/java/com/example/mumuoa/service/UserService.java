package com.example.mumuoa.service;

import java.util.Set;

public interface UserService {
    int registerUser(String registerCode, String code, String nickname, String photo);

    Set<String> searchUserPermissions(Integer userId);
}