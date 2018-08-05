package com.mj.zas.service;

import java.util.List;

import com.mj.zas.entity.ZAMenu;

public interface MenuService {

	String list();

	String save(ZAMenu menu);

	String delete(List<String> ids);

	List<ZAMenu> getAllMenu();

	String listParent();

	String listSon(String id);
}
