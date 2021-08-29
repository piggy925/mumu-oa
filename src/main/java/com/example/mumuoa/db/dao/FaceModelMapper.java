package com.example.mumuoa.db.dao;

import com.example.mumuoa.db.pojo.FaceModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FaceModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FaceModel record);

    int insertSelective(FaceModel record);

    FaceModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FaceModel record);

    int updateByPrimaryKey(FaceModel record);
}