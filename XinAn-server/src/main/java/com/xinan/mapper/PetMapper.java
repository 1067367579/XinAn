package com.xinan.mapper;

import com.xinan.entity.Pet;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PetMapper {
    @Insert("insert into XinAn.pet (name, image, user_id) " +
            "values (#{name},#{image},#{userId})")
    void insert(Pet pet);


    @Select("select id, name, image, user_id from XinAn.pet " +
            "where user_id = #{userId}")
    List<Pet> getByUserId(Long userId);

    @Select("select id, name, image, user_id from XinAn.pet " +
            "where id = #{id}")
    Pet getById(Long id);

    @Delete("delete from XinAn.pet where id = #{id}")
    void deleteById(Long id);

    void update(Pet pet);
}
