package com.xinan.service;

import com.xinan.vo.ProcessVO;

import java.util.List;

public interface ProcessService {
    List<ProcessVO> getByUserId(Long userId);

}
