package com.xinan.service.impl;

import com.xinan.entity.Process;
import com.xinan.entity.ProcessDetail;
import com.xinan.mapper.ProcessDetailMapper;
import com.xinan.mapper.ProcessMapper;
import com.xinan.service.ProcessService;
import com.xinan.vo.ProcessVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    private ProcessMapper processMapper;

    @Autowired
    private ProcessDetailMapper processDetailMapper;

    @Override
    public List<ProcessVO> getByUserId(Long userId) {
        List<Process> processes = processMapper.getByUserId(userId);
        List<ProcessVO> processVOs = new ArrayList<>();
        for (Process process : processes) {
            List<ProcessDetail> processDetail = processDetailMapper.getByProcessId(process.getId());
            ProcessVO processVO = ProcessVO.builder()
                    .id(process.getId())
                    .userId(process.getUserId())
                    .name(process.getName())
                    .status(process.getStatus())
                    .order(process.getOrder())
                    .illustrate(process.getIllustrate())
                    .detail(processDetail)
                    .build();
            processVOs.add(processVO);
        }
        processVOs.sort((o1, o2) -> o1.getStatus() - o2.getStatus());
        return processVOs;
    }
}
