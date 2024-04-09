package com.xinan.mapper;

import com.xinan.entity.Password;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PasswordMapper {

    @Insert("insert into password_box (user_id, account, password, platform_name, platform_image)" +
            " values (#{userId},#{account},#{password},#{platformName},#{platformImage})")
    void insert(Password password);


    void update(Password password);

    @Select("select id, user_id, account, password, platform_name, platform_image" +
            " from password_box where user_id = #{userId}")
    List<Password> getByUserId(Long userId);

    @Select("select id, user_id, account, password, platform_name, platform_image" +
            " from password_box where id = #{id}")
    Password getById(Long id);

    @Delete("delete from XinAn.password_box where id = #{id}")
    void deleteById(Long id);
}
