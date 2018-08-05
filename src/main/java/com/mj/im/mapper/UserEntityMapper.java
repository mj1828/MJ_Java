package com.mj.im.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.mj.im.entity.UserEntity;

@Mapper
public interface UserEntityMapper {
	int insert(UserEntity record);

	int insertSelective(UserEntity record);

	UserEntity findOne(String ip);

}