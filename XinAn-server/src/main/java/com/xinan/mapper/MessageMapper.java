package com.xinan.mapper;

import com.xinan.entity.Message;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MessageMapper {
    @Insert("insert into XinAn.message (sender_id, receiver_id, package_id, package_category)" +
            " values (#{senderId},#{receiverId},#{packageId},#{packageCategory})")
    void insert(Message message);

    @Select("select id, sender_id, receiver_id, package_id, package_category" +
            " from message where receiver_id = #{receiverId} " +
            "and package_category = #{packageCategory};")
    List<Message> getMessage(Message message);


    void deleteMessages(List<Long> messageIds);
}
