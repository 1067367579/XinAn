package com.xinan.mapper;

import com.xinan.entity.Process;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProcessMapper {

    void insertBatch(List<Process> processes);

    @Select("select id, user_id, name, illustrate, status " +
            "from XinAn.process where user_id = #{userId};")
    List<Process> getByUserId(Long userId);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into XinAn.process (user_id, name, illustrate, status) " +
            "values (#{userId},#{name},#{illustrate},#{status})")
    void insert(Process process);

    void deleteBatch(List<Long> ids);

    void update(Process process);
}
