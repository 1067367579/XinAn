package com.xinan.service;

import com.xinan.dto.WishDTO;
import com.xinan.entity.Wish;

public interface WishService {
    void addWish(WishDTO wishDTO);

    void updateWish(Wish wish);
}
