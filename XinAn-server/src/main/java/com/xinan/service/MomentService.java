package com.xinan.service;

import com.xinan.dto.MomentDTO;
import com.xinan.dto.MomentLikesDTO;
import com.xinan.vo.MomentVO;

import java.util.List;

public interface MomentService {
    void insertMoment(MomentDTO momentDTO);

    void updateMoment(MomentDTO momentDTO);

    void insertMomentLikes(MomentLikesDTO momentLikesDTO);

    void removeMomentLikes(Long id);

    List<MomentVO> listAll();

    List<MomentVO> getMomentsByUserId(Long userId);

    void deleteById(Long id);
}
