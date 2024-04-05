package com.xinan.mapper;

import com.xinan.entity.Wish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    void updateWish(Wish wish);

    @Delete("delete from wish where id = #{id}")
    void deleteById(Long id);

    @Select("select id, user_id, content, status from wish where id = #{id}")
    Wish getById(Long id);

    @Select("select id, user_id, content, status from wish where user_id = #{userId};")
    List<Wish> listByUser(Long userId);
}
