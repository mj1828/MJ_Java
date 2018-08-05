package com.mj.zas.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mj.zas.config.security.UrlGrantedAuthority;
import com.mj.zas.entity.ZAMenu;
import com.mj.zas.entity.ZAPermission;
import com.mj.zas.entity.ZARole;
import com.mj.zas.entity.ZAUser;
import com.mj.zas.mapper.ZAMenuMapper;
import com.mj.zas.mapper.ZAPermissionMapper;
import com.mj.zas.mapper.ZARoleMapper;
import com.mj.zas.mapper.ZAUserMapper;
import com.mj.zas.service.UserService;
import com.mj.zas.util.JsonUtil;
import com.mj.zas.util.ResultUtil;

/**
 * @ClassName: UserServiceImpl
 * @Description:
 * @author: Zyc
 * @date: 2018年1月13日 下午1:18:20
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	ZAUserMapper userMapper;
	@Autowired
	ZAPermissionMapper permissionMapper;
	@Autowired
	ZAMenuMapper menuMapper;
	@Autowired
	ZARoleMapper roleMapper;

	@Override
	public boolean insert(ZAUser record) {
		return userMapper.insert(record) == 0 ? true : false;
	}

	@Override
	public ZAUser selectByName(String name) {
		return userMapper.selectByName(name);
	}

	@Override
	public String login(ZAUser user) {
		if (user != null) {
			if (user.getUserName() == null) {
				return ResultUtil.fail("用户名不能为空！");
			}
			if (user.getPassword() == null) {
				return ResultUtil.fail("密码不能为空！");
			}
			ZAUser zaUser = userMapper.selectByName(user.getUserName());
			if (zaUser != null) {
				if (zaUser.getPassword().equals(user.getPassword())) {
					return ResultUtil.success("登录成功！");
				} else {
					return ResultUtil.fail("用户名或密码错误！");
				}
			} else {
				return ResultUtil.fail("用户名或密码错误！");
			}
		}
		return ResultUtil.fail("登录信息不能为空！");
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ZAUser user = userMapper.selectByName(username);
		if (user != null) {
			ZARole role = roleMapper.getRoleById(user.getRoleId());
			if (role != null) {
				List<ZAPermission> permissions = permissionMapper.getByRoleId(role.getId());
				List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
				for (ZAPermission permission : permissions) {
					if (permission != null) {
						ZAMenu menu = menuMapper.selectById(permission.getMenuId());
						if (menu != null) {
							GrantedAuthority grantedAuthority = new UrlGrantedAuthority(menu.getPath());
							grantedAuthorities.add(grantedAuthority);
						}
					}
				}
				return new User(user.getUserName(), user.getPassword(), grantedAuthorities);
			} else {
				throw new UsernameNotFoundException("admin: " + username + " do not exist!");
			}
		} else {
			throw new UsernameNotFoundException("admin: " + username + " do not exist!");
		}
	}

	@Override
	public String List(int pageNum, int pageSize) {
		PageHelper.offsetPage(pageNum * pageSize, pageSize);
		List<ZAUser> list = userMapper.list();
		return ResultUtil.success(JsonUtil.toJsonString(new PageInfo(list)));
	}

	@Override
	public String userById(String id) {
		ZAUser user = userMapper.userById(id);
		return ResultUtil.success(JsonUtil.toJsonString(user));
	}

	@Override
	public String save(ZAUser user) {
		if (user != null) {
			if (user.getId() != null) {
				user.setUpdateTime(new Date());
				user.setUpdateUser("Admin");
				int result = userMapper.updateByPrimaryKeySelective(user);
				if (result > 0) {
					return ResultUtil.success("更新用户成功！");
				}
				return ResultUtil.fail("更新用户失败！");
			} else {
				user.setAddTime(new Date());
				user.setAddUser("Admin");
				int result = userMapper.insert(user);
				if (result > 0) {
					return ResultUtil.success("添加用户成功！");
				}
				return ResultUtil.fail("添加用户失败！");
			}
		}
		return ResultUtil.fail("操作用户信息失败！");
	}

	@Override
	public String delete(String id) {
		String message = "删除用户信息失败！";
		if (StringUtils.isEmpty(id)) {
			message = "ID 为空，删除用户信息失败！";
		} else {
			int result = userMapper.deleteByPrimaryKey(Integer.parseInt(id));
			if (result > 0) {
				message = "删除用户信息成功！";
				return ResultUtil.success(message);
			}
		}
		return ResultUtil.fail(message);
	}

}
