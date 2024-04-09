package com.xinan.service;

import com.xinan.dto.PlatformDTO;
import com.xinan.entity.Platform;

import java.util.List;

public interface PlatformService {

    void addPlatform(PlatformDTO platformDTO);

    List<Platform> listAll(Long userId);

    void update(Platform platform);

    void deleteById(Long id);

    Platform getById(Long id);
}
