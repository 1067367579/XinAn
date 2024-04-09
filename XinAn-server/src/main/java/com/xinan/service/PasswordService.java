package com.xinan.service;

import com.xinan.dto.MessageDTO;
import com.xinan.dto.PasswordDTO;
import com.xinan.entity.Password;
import com.xinan.vo.PasswordSendVO;

import java.util.List;

public interface PasswordService {
    void addPassword(PasswordDTO passwordDTO);

    void updatePassword(Password password);

    List<Password> listAll(Long userId);

    Password getById(Long id);

    void deleteById(Long id);

    void sendPassword(MessageDTO messageDTO);

    List<PasswordSendVO> listMessage(Long userId);

    void receivePassword(Long id);
}
