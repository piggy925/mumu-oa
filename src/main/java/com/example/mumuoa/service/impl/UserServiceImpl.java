package com.example.mumuoa.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.mumuoa.db.dao.UserMapper;
import com.example.mumuoa.exception.MumuoaException;
import com.example.mumuoa.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;

@Service
@Slf4j
@Scope("prototype")
public class UserServiceImpl implements UserService {
    @Value("${wx.app-id}")
    private String appId;
    @Value("${wx.app-secret}")
    private String appSecret;

    @Resource
    private UserMapper userMapper;

    /**
     * 获取openId.
     * <p>
     * 官方文档链接：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/login/auth.code2Session.html
     */
    private String getOpenId(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        HashMap map = new HashMap();
        map.put("appid", appId);
        map.put("secret", appSecret);
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");

        String response = HttpUtil.post(url, map);
        JSONObject json = JSONUtil.parseObj(response);
        String openid = json.getStr("openid");
        if (ObjectUtils.isEmpty(openid)) {
            throw new RuntimeException("登录凭证有错误");
        }
        return openid;
    }


    @Override
    public int registerUser(String registerCode, String code, String nickname, String photo) {
        if (registerCode.equals("000000")) {
            if (!userMapper.haveRootUser()) {
                String openId = getOpenId(code);
                HashMap param = new HashMap();
                param.put("openId", openId);
                param.put("nickname", nickname);
                param.put("photo", photo);
                param.put("role", "[0]");
                param.put("status", 1);
                param.put("createTime", new Date());
                param.put("root", true);
                userMapper.insert(param);
                Integer id = userMapper.searchIdByOpenId(openId);
                return id;
            } else {
                throw new MumuoaException("已存在超级管理员账号");
            }
        } else {
            //TODO 普通员工注册流程
        }

        return 0;
    }
}