package com.mj.zas.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mj.zas.entity.ZAPermission;

@Mapper
public interface ZAPermissionMapper {
	int insert(ZAPermission record);

	int insertSelective(ZAPermission record);

	List<ZAPermission> getByRoleId(Integer id);
}