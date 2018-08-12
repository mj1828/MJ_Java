package com.mj.im.mapper;

import com.mj.im.entity.ZCSensitiveWord;

public interface ZCSensitiveWordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ZCSensitiveWord record);

    int insertSelective(ZCSensitiveWord record);

    ZCSensitiveWord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ZCSensitiveWord record);

    int updateByPrimaryKey(ZCSensitiveWord record);
}