package com.xinan.mapper;

import com.xinan.entity.Platform;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PlatformMapper {

    @Insert("insert into platform (user_id, name, image, status)" +
            " values (#{userId},#{name},#{image},#{status})")
    void insert(Platform platform);

    @Select("select id, user_id, name, image, status " +
            "from platform where user_id = #{userId}")
    List<Platform> getByUserId(Long userId);

    void update(Platform platform);

    @Delete("delete from XinAn.platform where id = #{id}")
    void deleteById(Long id);

    @Select("select id, user_id, name, image, status" +
            " from XinAn.platform where id = #{id}")
    Platform getById(Long id);
}
