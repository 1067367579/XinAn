package com.xinan.service;

import com.xinan.dto.WishDTO;
import com.xinan.entity.Wish;

import java.util.List;

public interface WishService {
    void addWish(WishDTO wishDTO);

    void updateWish(Wish wish);

    void deleteWish(Long id);

    Wish getById(Long id);

    List<Wish> listByUser(Long userId);
}
