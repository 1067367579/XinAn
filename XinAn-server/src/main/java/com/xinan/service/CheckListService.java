package com.xinan.service;

import com.xinan.dto.CheckListDTO;
import com.xinan.vo.CheckListVO;

public interface CheckListService {
    CheckListVO getByUserId(Long id);

    void updateList(CheckListDTO checkListDTO);
}
