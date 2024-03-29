package com.xinan.mapper;

import com.xinan.dto.FriendDTO;
import com.xinan.entity.Friend;
import com.xinan.vo.FriendVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FriendMapper {

    @Select("select count(*) from XinAn.friend where user_id = #{userId}")
    Integer getCountByUserId(Long userId);

    @Select("select user.id,friend.remark_name,user.avatar,user.phone,friend.friend_category_id " +
            "from XinAn.friend,XinAn.user where friend.user_id = #{userId} and friend_id = user.id")
    List<FriendVO> listFriends(Long userId);

    @Insert("insert into XinAn.friend (user_id, friend_id, friend_category_id, remark_name, create_time) " +
            "values (#{userId},#{friendId},#{friendCategoryId},#{remarkName},#{createTime})")
    void insert(Friend friend);

    void update(Friend friend);
}
