package com.mj.zas.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mj.zas.entity.ZAUser;

@Mapper
public interface ZAUserMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(ZAUser record);

	int insertSelective(ZAUser record);

	ZAUser selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ZAUser record);

	int updateByPrimaryKey(ZAUser record);

	ZAUser selectByName(String name);

	List<ZAUser> list();

	ZAUser userById(String id);
}