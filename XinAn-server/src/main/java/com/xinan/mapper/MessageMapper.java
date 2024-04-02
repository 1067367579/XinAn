package com.xinan.mapper;

import com.xinan.entity.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper {
    @Insert("insert into XinAn.message (sender_id, receiver_id, package_id, package_category)" +
            " values (#{senderId},#{receiverId},#{packageId},#{packageCategory})")
    void insert(Message message);
}
