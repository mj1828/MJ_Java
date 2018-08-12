package com.mj.im.mapper;

import com.mj.im.entity.ZCChatRoomMember;
import com.mj.im.entity.ZCChatRoomMemberKey;

public interface ZCChatRoomMemberMapper {
    int deleteByPrimaryKey(ZCChatRoomMemberKey key);

    int insert(ZCChatRoomMember record);

    int insertSelective(ZCChatRoomMember record);

    ZCChatRoomMember selectByPrimaryKey(ZCChatRoomMemberKey key);

    int updateByPrimaryKeySelective(ZCChatRoomMember record);

    int updateByPrimaryKey(ZCChatRoomMember record);
}