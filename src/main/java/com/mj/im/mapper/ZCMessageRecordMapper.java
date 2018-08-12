package com.mj.im.mapper;

import com.mj.im.entity.ZCMessageRecord;

public interface ZCMessageRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ZCMessageRecord record);

    int insertSelective(ZCMessageRecord record);

    ZCMessageRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ZCMessageRecord record);

    int updateByPrimaryKey(ZCMessageRecord record);
}