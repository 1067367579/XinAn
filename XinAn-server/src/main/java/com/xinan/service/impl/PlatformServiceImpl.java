package com.xinan.service.impl;

import com.xinan.constant.UserConstant;
import com.xinan.context.BaseContext;
import com.xinan.dto.PlatformDTO;
import com.xinan.entity.Platform;
import com.xinan.exception.BaseException;
import com.xinan.mapper.PlatformMapper;
import com.xinan.service.PlatformService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PlatformServiceImpl implements PlatformService {

    @Autowired
    private PlatformMapper platformMapper;

    @Override
    public void addPlatform(PlatformDTO platformDTO) {
        if(platformDTO.getName()==null || platformDTO.getName().isEmpty())
        {
            throw new BaseException(UserConstant.PLATFORM_NAME_EMPTY);
        }
        Long userId = BaseContext.getCurrentId();
        log.info("当前添加平台的用户:{}",userId);
        Platform platform = Platform.builder()
                .name(platformDTO.getName())
                .image(platformDTO.getImage())
                .userId(userId)
                .status(UserConstant.DEFAULT_PLATFORM_STATUS)
                .build();
        platformMapper.insert(platform);
    }

    @Override
    public List<Platform> listAll(Long userId) {
        return platformMapper.getByUserId(userId);
    }

    @Override
    public void update(Platform platform) {
        if(platform.getName()==null || platform.getName().isEmpty())
        {
            throw new BaseException(UserConstant.PLATFORM_NAME_EMPTY);
        }
        platformMapper.update(platform);
    }

    @Override
    public void deleteById(Long id) {
        platformMapper.deleteById(id);
    }

    @Override
    public Platform getById(Long id) {
        return platformMapper.getById(id);
    }


}
