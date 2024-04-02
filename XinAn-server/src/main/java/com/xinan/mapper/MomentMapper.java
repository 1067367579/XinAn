package com.xinan.mapper;

import com.xinan.entity.Moment;
import com.xinan.entity.MomentLikes;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MomentMapper {

    @Insert("insert into XinAn.moment (user_id, title, sub_title, photo1, photo2, photo3, photo4, begin_date, end_date, create_time)" +
            " values (#{userId},#{title},#{subTitle},#{photo1},#{photo2},#{photo3},#{photo4},#{beginDate},#{endDate},#{createTime}) ")
    void insertMoment(Moment moment);

    void updateMoment(Moment moment);

    @Insert("insert into XinAn.moment_likes (user_id, moment_id, create_time)" +
            " values (#{userId},#{momentId},#{createTime})")
    void insertLikes(MomentLikes momentLikes);

    @Delete("delete from XinAn.moment_likes where user_id = #{userId} and " +
            "moment_id = #{momentId} ")
    void deleteLikes(MomentLikes momentLikes);

    @Select("select id, user_id, title, sub_title, photo1, photo2, photo3, photo4, begin_date, end_date, create_time" +
            " from moment order by create_time desc")
    List<Moment> listAll();

    @Select("select id, user_id, moment_id, create_time " +
            "from moment_likes where moment_id = #{momentId}")
    List<MomentLikes> getLikesByMomentId(Long momentId);

    @Select("select id, user_id, title, sub_title, photo1, photo2, photo3, photo4, begin_date, end_date, create_time" +
            " from XinAn.moment where user_id = #{userId}")
    List<Moment> getByUserId(Long userId);

    @Delete("delete from XinAn.moment where id = #{id}")
    void deleteById(Long id);
}
