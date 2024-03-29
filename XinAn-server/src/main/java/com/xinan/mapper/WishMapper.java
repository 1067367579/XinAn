package com.xinan.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface WishMapper {
    /**
     * 获取当前用户的愿望数
     */
    @Select("select count(*) from XinAn.wish where user_id = #{userId}")
    Integer getCountByUserId(Long userId);
}
