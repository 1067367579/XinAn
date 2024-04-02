package com.xinan.service.impl;

import com.xinan.context.BaseContext;
import com.xinan.dto.InvitationDTO;
import com.xinan.entity.Friend;
import com.xinan.entity.FriendCategory;
import com.xinan.mapper.FriendCategoryMapper;
import com.xinan.mapper.FriendMapper;
import com.xinan.service.InvitationService;
import com.xinan.vo.FriendInvitationVO;
import com.xinan.vo.FriendVO;
import com.xinan.vo.InvitationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class InvitationServiceImpl implements InvitationService {

    @Autowired
    private FriendMapper friendMapper;

    @Autowired
    private FriendCategoryMapper friendCategoryMapper;

    @Override
    public void updateInvite(InvitationDTO invitationDTO) {
        Long userId = BaseContext.getCurrentId();
        Long friendId = invitationDTO.getFriendId();
        Integer invited = invitationDTO.getInvited();
        Friend friend = Friend.builder()
                .userId(userId)
                .friendId(friendId)
                .invited(invited)
                .build();
        friendMapper.update(friend);
    }

    @Override
    public List<InvitationVO> listAllByCategoryId(Long id) {
        List<FriendVO> friends = friendMapper.listFriends(id);
        //TODO 好友分类封装
        Map<Long,List<FriendInvitationVO>> map = new HashMap<>();
        for (FriendVO friend : friends) {
            if(!map.containsKey(friend.getFriendCategoryId()))
            {
                List<FriendInvitationVO> list = new ArrayList<>();
                list.add(FriendInvitationVO.builder()
                                .friendId(friend.getId())
                                .invited(friend.getInvited())
                                .remarkName(friend.getRemarkName())
                        .build());
                map.put(friend.getFriendCategoryId(),list);
            }
            else
            {
                List<FriendInvitationVO> list = map.get(friend.getFriendCategoryId());
                list.add(FriendInvitationVO.builder()
                        .friendId(friend.getId())
                        .invited(friend.getInvited())
                        .remarkName(friend.getRemarkName())
                        .build());
                map.put(friend.getFriendCategoryId(), list);
            }
        }
        List<FriendCategory> categories = friendCategoryMapper.getByUserId(id);
        Map<Long,String> categoryMap = new HashMap<>();
        for (FriendCategory category : categories) {
            categoryMap.put(category.getId(), category.getName());
        }
        List<InvitationVO> res = new ArrayList<>();
        for (Map.Entry<Long, List<FriendInvitationVO>> entry : map.entrySet()) {
            res.add(InvitationVO.builder()
                            .categoryName(categoryMap.get(entry.getKey()))
                            .friendCategoryId(entry.getKey())
                            .friends(entry.getValue())
                    .build());
        }
        return res;
    }
}
