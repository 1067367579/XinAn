package com.xinan.mapper;

import com.xinan.entity.Treasure;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TreasureMapper {
    @Insert("insert into XinAn.treasure (name, image, user_id) values (#{name},#{image},#{userId})")
    void insert(Treasure treasure);

    @Select("select id, name, image, user_id from treasure where user_id = #{userId}")
    List<Treasure> listByUser(Long userId);

    @Select("select id, name, image, user_id from treasure where id = #{id}")
    Treasure getById(Long id);

    @Delete("delete from treasure where id = #{id}")
    void deleteById(Long id);

    @Update("update XinAn.treasure set name = #{name}, image = #{image} where id = #{id}")
    void update(Treasure treasure);
}
