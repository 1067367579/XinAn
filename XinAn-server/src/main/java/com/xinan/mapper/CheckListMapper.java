package com.xinan.mapper;

import com.xinan.dto.CheckListDTO;
import com.xinan.entity.CheckList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CheckListMapper {
    /**
     * 用户初始化时插入数据
     */
    @Insert("insert into XinAn.check_list (user_id, invite_state, wish_state, social_platform_state, password_box_state, pet_state, treasure_state) " +
            "values (#{userId},#{inviteState},#{wishState},#{socialPlatformState},#{passwordBoxState},#{petState},#{treasureState})")
    void insertUser(CheckList checkList);

    /**
     * 获取当前用户已完成的条目数据
     */
    @Select("select id, user_id, invite_state, wish_state, social_platform_state, password_box_state, pet_state, treasure_state" +
            " from XinAn.check_list where user_id = #{userId}")
    CheckList getByUserId(Long userId);

    void update(CheckListDTO checkListDTO);
}
