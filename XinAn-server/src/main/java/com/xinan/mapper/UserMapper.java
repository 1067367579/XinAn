package com.xinan.mapper;

import com.xinan.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    @Select("select id, openid, username, phone, gender, birthday, avatar, signature, background_image, create_time" +
            " from XinAn.user where openid = #{openid}")
    User getByOpenid(String openid);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into XinAn.user (openid, username, phone, gender, birthday, avatar, signature, background_image, create_time) " +
            "values (#{openid},#{username},#{phone},#{gender},#{birthday},#{avatar},#{signature},#{backgroundImage},#{createTime})")
    void insert(User user);

    @Select("select id, openid, username, phone, gender, birthday, avatar, signature, background_image, create_time" +
            " from XinAn.user where id = #{id}")
    User getById(Long id);

    void update(User user);


    @Select("select id, openid, username, phone, gender, birthday, avatar, signature, background_image, create_time" +
            " from XinAn.user where phone = #{phone}")
    User getByPhone(String phone);
}
