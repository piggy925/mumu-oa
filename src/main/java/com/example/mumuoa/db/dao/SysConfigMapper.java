package com.example.mumuoa.db.dao;

import com.example.mumuoa.db.pojo.SysConfig;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysConfigMapper {
    List<SysConfig> selectAllParam();
}