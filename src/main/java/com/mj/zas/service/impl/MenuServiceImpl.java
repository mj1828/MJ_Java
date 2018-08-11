package com.mj.zas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mj.util.JsonUtil;
import com.mj.util.ResultUtil;
import com.mj.zas.entity.ZAMenu;
import com.mj.zas.mapper.ZAMenuMapper;
import com.mj.zas.service.MenuService;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

	@Autowired
	private ZAMenuMapper menuMapper;

	@Override
	public String list() {
		List<ZAMenu> list = menuMapper.list();
		return ResultUtil.success(JsonUtil.toJsonString(list));
	}

	@Override
	public String listParent() {
		List<ZAMenu> list = menuMapper.listParent();
		return ResultUtil.success(JsonUtil.toJsonString(list));
	}

	@Override
	public String save(ZAMenu menu) {
		int result;
		if (menu != null) {
			if (menu.getId() > 0) {
				result = menuMapper.update(menu);
				if (result > 0) {
					return ResultUtil.success("保存成功！");
				} else {
					return ResultUtil.fail("保存失败！");
				}
			} else {
				result = menuMapper.add(menu);
				if (result > 0) {
					return ResultUtil.success("添加成功！");
				} else {
					return ResultUtil.fail("添加失败！");
				}
			}
		} else {
			return ResultUtil.fail("添加失败！");
		}

	}

	@Override
	public String delete(List<String> ids) {
		int result = menuMapper.delete(ids);
		if (result > 0) {
			return ResultUtil.success("删除成功！");
		} else {
			return ResultUtil.fail("删除失败！");
		}
	}

	@Override
	public List<ZAMenu> getAllMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String listSon(String id) {
		List<ZAMenu> list = menuMapper.listSon(id);
		return ResultUtil.success(JsonUtil.toJsonString(list));
	}

}
