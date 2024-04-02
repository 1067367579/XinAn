package com.xinan.service;

import com.xinan.dto.*;
import com.xinan.entity.FriendCategory;
import com.xinan.entity.User;
import com.xinan.vo.FriendCategoryVO;
import com.xinan.vo.FriendVO;
import com.xinan.vo.MerchantVO;
import com.xinan.vo.UserVO;

import java.util.List;

public interface UserService {
    User wxLogin(UserLoginDTO userLoginDTO);

    UserVO getById(Long id);

    Long getDayToBirthday(Long id);

    void updateUser(UserDTO userDTO);

    List<FriendVO> listFriends(Long id);

    UserVO getUserByPhone(String phone);

    void addFriend(FriendDTO friendDTO);

    UserVO getCardById(Long id);

    void updateFriend(FriendDTO friendDTO);

    void insertFriendCategory(FriendCategoryDTO friendCategoryDTO);

    List<FriendCategoryVO> getFriendCategories(Long id);

    List<MerchantVO> getFavorites(Long id);

    void addFavorite(UserMerchantDTO userMerchantDTO);

    void updateFriendCategory(FriendCategory friendCategory);

    void deleteFriendCategoryById(Long id);

    void deleteFriendById(Long friendId);

    void sendRequest(MessageDTO messageDTO);
}
