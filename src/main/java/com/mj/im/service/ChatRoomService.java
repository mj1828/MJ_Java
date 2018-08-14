package com.mj.im.service;

import com.mj.im.entity.ZCChatRoom;

public interface ChatRoomService {

	String List(int pageNum, int pageSize);

	String chatRoomById(String id);

	String save(ZCChatRoom chatRoom);

	String delete(String id);

}
