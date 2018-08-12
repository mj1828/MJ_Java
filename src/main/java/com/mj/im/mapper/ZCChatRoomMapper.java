package com.mj.im.mapper;

import com.mj.im.entity.ZCChatRoom;

public interface ZCChatRoomMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ZCChatRoom record);

    int insertSelective(ZCChatRoom record);

    ZCChatRoom selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ZCChatRoom record);

    int updateByPrimaryKey(ZCChatRoom record);
}