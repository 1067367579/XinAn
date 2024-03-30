package com.xinan.service.impl;

import com.xinan.constant.UserConstant;
import com.xinan.dto.CheckListDTO;
import com.xinan.entity.CheckList;
import com.xinan.mapper.CheckListMapper;
import com.xinan.service.CheckListService;
import com.xinan.vo.CheckListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CheckListServiceImpl implements CheckListService {

    @Autowired
    private CheckListMapper checkListMapper;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public CheckListVO getByUserId(Long id) {
        CheckList checkList = checkListMapper.getByUserId(id);
        Integer count = userService.getFinishedCount(checkList);
        double listProcess = count.doubleValue()/ UserConstant.CHECKLIST_COUNT*100;
        CheckListVO vo = new CheckListVO();
        BeanUtils.copyProperties(checkList,vo);
        vo.setListProcess((int)listProcess);
        return vo;
    }

    @Override
    public void updateList(CheckListDTO checkListDTO) {
        checkListMapper.update(checkListDTO);
    }
}
