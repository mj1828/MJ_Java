package com.mj.zas.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mj.zas.entity.ZAMenu;

@Mapper
public interface ZAMenuMapper {

	ZAMenu selectById(Integer menuId);

	List<ZAMenu> list();

	int delete(List<String> ids);

	int update(ZAMenu menu);

	int add(ZAMenu menu);

	List<ZAMenu> listParent();

	List<ZAMenu> listSon(String id);
}