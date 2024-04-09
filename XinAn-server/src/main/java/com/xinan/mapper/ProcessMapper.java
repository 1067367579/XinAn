package com.xinan.mapper;

import com.xinan.entity.Process;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProcessMapper {

    void insertBatch(List<Process> processes);

    @Select("select id, user_id, name, illustrate, status, `order` " +
            "from XinAn.process where user_id = #{userId};")
    List<Process> getByUserId(Long userId);
}
