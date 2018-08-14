package com.mj.im.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mj.im.entity.ZCChatRoom;
import com.mj.im.mapper.ZCChatRoomMapper;
import com.mj.im.service.ChatRoomService;
import com.mj.util.JsonUtil;
import com.mj.util.ResultUtil;

@Service("chatRoomService")
public class ChatRoomServiceImpl implements ChatRoomService {

	@Autowired
	private ZCChatRoomMapper chatRoomMapper;

	@Override
	public String List(int pageNum, int pageSize) {
		PageHelper.offsetPage(pageNum * pageSize, pageSize);
		List<ZCChatRoom> list = chatRoomMapper.list();
		return ResultUtil.success(JsonUtil.toJsonString(new PageInfo(list)));
	}

	@Override
	public String chatRoomById(String id) {
		ZCChatRoom chatRoom = chatRoomMapper.chatRoomById(id);
		return ResultUtil.success(JsonUtil.toJsonString(chatRoom));
	}

	@Override
	public String save(ZCChatRoom chatRoom) {
		if (chatRoom != null) {
			if (chatRoom.getId() != null) {
				chatRoom.setModifytime(new Date());
				chatRoom.setModifyuser("Admin");
				int result = chatRoomMapper.updateByPrimaryKeySelective(chatRoom);
				if (result > 0) {
					return ResultUtil.success("更新聊天室成功！");
				}
				return ResultUtil.fail("更新聊天室失败！");
			} else {
				chatRoom.setAddtime(new Date());
				chatRoom.setAdduser("Admin");
				int result = chatRoomMapper.insert(chatRoom);
				if (result > 0) {
					return ResultUtil.success("添加聊天室成功！");
				}
				return ResultUtil.fail("添加聊天室失败！");
			}
		}
		return ResultUtil.fail("操作聊天室信息失败！");
	}

	@Override
	public String delete(String id) {
		String message = "聊天室删除失败！";
		if (StringUtils.isEmpty(id)) {
			message = "ID 为空，聊天室删除失败！";
		} else {
			int result = chatRoomMapper.deleteByPrimaryKey(Long.parseLong(id));
			if (result > 0) {
				message = "聊天室删除成功！";
				return ResultUtil.success(message);
			}
		}
		return ResultUtil.fail(message);
	}

}
