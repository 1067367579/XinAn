package com.xinan.service;

import com.xinan.dto.InvitationDTO;
import com.xinan.vo.InvitationVO;

import java.util.List;

public interface InvitationService {
    void updateInvite(InvitationDTO invitationDTO);

    List<InvitationVO> listAllByCategoryId(Long id);
}
