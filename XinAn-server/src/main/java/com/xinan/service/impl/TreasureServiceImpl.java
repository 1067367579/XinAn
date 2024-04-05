package com.xinan.service.impl;

import com.xinan.constant.MessageConstant;
import com.xinan.constant.UserConstant;
import com.xinan.context.BaseContext;
import com.xinan.dto.TreasureDTO;
import com.xinan.entity.Treasure;
import com.xinan.exception.BaseException;
import com.xinan.mapper.TreasureMapper;
import com.xinan.service.TreasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TreasureServiceImpl implements TreasureService {

    @Autowired
    private TreasureMapper treasureMapper;

    @Override
    public void addTreasure(TreasureDTO treasureDTO) {
        Long userId = BaseContext.getCurrentId();
        if(treasureDTO.getName() == null ||treasureDTO.getName().isEmpty())
        {
            throw new BaseException(MessageConstant.TREASURE_EMPTY);
        }
        if(treasureDTO.getImage() == null|| treasureDTO.getImage().isEmpty())
        {
            treasureDTO.setImage(UserConstant.DEFAULT_TREASURE_IMAGE);
        }
        Treasure treasure = Treasure.builder()
                .name(treasureDTO.getName())
                .image(treasureDTO.getImage())
                .userId(userId)
                .build();
        treasureMapper.insert(treasure);
    }

    @Override
    public List<Treasure> listAll(Long userId) {
        return treasureMapper.listByUser(userId);
    }

    @Override
    public Treasure getById(Long id) {
        return treasureMapper.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        treasureMapper.deleteById(id);
    }

    @Override
    public void update(Treasure treasure) {
        String name = treasure.getName();
        String image = treasure.getImage();
        if(name==null || name.isEmpty())
        {
            throw new BaseException(MessageConstant.TREASURE_EMPTY);
        }
        if(image == null || image.isEmpty())
        {
            treasure.setImage(UserConstant.DEFAULT_TREASURE_IMAGE);
        }
        treasureMapper.update(treasure);
    }
}
