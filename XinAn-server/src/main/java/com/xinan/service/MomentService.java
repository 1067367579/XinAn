package com.xinan.service;

import com.xinan.dto.MomentDTO;
import com.xinan.dto.MomentLikesDTO;
import com.xinan.result.PageResult;

public interface MomentService {
    void insertMoment(MomentDTO momentDTO);

    void updateMoment(MomentDTO momentDTO);

    void insertMomentLikes(MomentLikesDTO momentLikesDTO);

    void removeMomentLikes(Long momentId);

    PageResult listAll(Integer page, Integer pageSize);

    PageResult getMomentsByUserId(Long userId,Integer page,Integer pageSize);

    void deleteById(Long id);
}
