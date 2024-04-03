package com.xinan.service.impl;

import com.xinan.constant.MessageConstant;
import com.xinan.constant.StatusConstant;
import com.xinan.constant.UserConstant;
import com.xinan.context.BaseContext;
import com.xinan.dto.WishDTO;
import com.xinan.entity.Wish;
import com.xinan.exception.BaseException;
import com.xinan.mapper.WishMapper;
import com.xinan.service.WishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WishServiceImpl implements WishService {

    @Autowired
    private WishMapper wishMapper;

    @Override
    public void addWish(WishDTO wishDTO) {
        String content = wishDTO.getContent();
        if(content == null || content.isEmpty())
        {
            throw new BaseException(MessageConstant.WISH_EMPTY);
        }
        //愿望不为空 组装数据插入表中
        Long userId = BaseContext.getCurrentId();
        Integer status = StatusConstant.NOT_FINISHED;
        Wish wish = Wish.builder()
                .userId(userId)
                .status(status)
                .content(content)
                .build();
        wishMapper.insertWish(wish);
    }

    @Override
    public void updateWish(Wish wish) {
        Long userId = BaseContext.getCurrentId();
        wish.setUserId(userId);
        
    }
}
