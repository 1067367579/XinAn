package com.xinan.mapper;

import com.xinan.entity.ProcessDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProcessDetailMapper {

    @Select("select id, process_id, title, content, `order` " +
            "from process_detail where process_id = #{processId}")
    List<ProcessDetail> getByProcessId(Long processId);
}
