package com.xinan.mapper;

import com.xinan.entity.Wish;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface WishMapper {
    /**
     * 获取当前用户的愿望数
     */
    @Select("select count(*) from XinAn.wish where user_id = #{userId}")
    Integer getCountByUserId(Long userId);

    @Insert("insert into XinAn.wish (user_id, content, status) values" +
            "(#{userId},#{content},#{status})")
    void insertWish(Wish wish);
}
