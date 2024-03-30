package com.xinan.service.impl;

import com.xinan.context.BaseContext;
import com.xinan.dto.InvitationDTO;
import com.xinan.entity.Friend;
import com.xinan.mapper.FriendMapper;
import com.xinan.service.InvitationService;
import com.xinan.vo.FriendVO;
import com.xinan.vo.InvitationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class InvitationServiceImpl implements InvitationService {

    @Autowired
    private FriendMapper friendMapper;
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
        return null;
    }
}
