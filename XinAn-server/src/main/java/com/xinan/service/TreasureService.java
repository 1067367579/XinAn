package com.xinan.service;

import com.xinan.dto.TreasureDTO;
import com.xinan.entity.Treasure;

import java.util.List;

public interface TreasureService {
    void addTreasure(TreasureDTO treasureDTO);

    List<Treasure> listAll(Long userId);

    Treasure getById(Long id);

    void deleteById(Long id);

    void update(Treasure treasure);
}
