package com.xinan.mapper;

import com.xinan.entity.Process;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProcessMapper {

    void insertBatch(List<Process> processes);
}
