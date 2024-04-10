package com.xinan.mapper;

import com.xinan.entity.ProcessDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProcessDetailMapper {

    @Select("select id, process_id, title, content " +
            "from process_detail where process_id = #{processId}")
    List<ProcessDetail> getByProcessId(Long processId);

    void deleteBatchByProcessIds(List<Long> ids);

    @Insert("insert into XinAn.process_detail (process_id, title, content) " +
            "values (#{processId},#{title},#{content})")
    void insert(ProcessDetail processDetail);

    void update(ProcessDetail processDetail);

    void deleteBatch(List<Long> ids);

    void insertBatch(List<ProcessDetail> processDetails);
}
