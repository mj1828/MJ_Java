package com.mj.zas.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.mj.zas.entity.ZARole;

@Mapper
public interface ZARoleMapper {
	int insert(ZARole record);

	int insertSelective(ZARole record);

	ZARole getRoleById(Integer roleId);

}