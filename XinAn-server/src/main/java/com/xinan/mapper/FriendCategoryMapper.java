package com.xinan.mapper;

import com.xinan.entity.FriendCategory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FriendCategoryMapper {
    @Insert("insert into XinAn.friend_category (user_id, name) values (#{userId},#{name})")
    void insert(FriendCategory friendCategory);

    @Select("select id, user_id, name from XinAn.friend_category where user_id = #{userId}")
    List<FriendCategory> getByUserId(Long userId);
}
