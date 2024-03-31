package com.xinan.mapper;

import com.xinan.entity.FriendCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FriendCategoryMapper {
    @Insert("insert into XinAn.friend_category (user_id, name) values (#{userId},#{name})")
    void insert(FriendCategory friendCategory);

    @Select("select id, user_id, name from XinAn.friend_category where user_id = #{userId}")
    List<FriendCategory> getByUserId(Long userId);

    @Update("update friend_category set name = #{name} where id = #{id};")
    void update(FriendCategory friendCategory);

    @Delete("delete from friend_category where id = #{id}")
    void deleteById(Long id);

    @Select("select * from friend_category where id = #{id}")
    FriendCategory getById(Long id);
}
