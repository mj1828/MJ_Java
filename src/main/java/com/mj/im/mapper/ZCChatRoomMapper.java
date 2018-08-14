package com.mj.im.mapper;

import java.util.List;

import com.mj.im.entity.ZCChatRoom;

public interface ZCChatRoomMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ZCChatRoom record);

    int insertSelective(ZCChatRoom record);

    ZCChatRoom selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ZCChatRoom record);

    int updateByPrimaryKey(ZCChatRoom record);

	List<ZCChatRoom> list();

	ZCChatRoom chatRoomById(String id);
}