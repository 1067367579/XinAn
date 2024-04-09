package com.xinan.service.impl;

import com.xinan.constant.UserConstant;
import com.xinan.context.BaseContext;
import com.xinan.dto.MessageDTO;
import com.xinan.dto.PasswordDTO;
import com.xinan.entity.Friend;
import com.xinan.entity.Message;
import com.xinan.entity.Password;
import com.xinan.entity.User;
import com.xinan.exception.BaseException;
import com.xinan.mapper.FriendMapper;
import com.xinan.mapper.MessageMapper;
import com.xinan.mapper.PasswordMapper;
import com.xinan.mapper.UserMapper;
import com.xinan.service.PasswordService;
import com.xinan.vo.PasswordSendVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PasswordServiceImpl implements PasswordService {

    @Autowired
    private PasswordMapper passwordMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private FriendMapper friendMapper;

    @Autowired
    private UserMapper userMapper;


    @Override
    public void addPassword(PasswordDTO passwordDTO) {
        if(passwordDTO.getPassword() == null ||
        passwordDTO.getPassword().isEmpty())
        {
            throw new BaseException(UserConstant.PASSWORD_EMPTY);
        }
        if(passwordDTO.getAccount() == null ||
                passwordDTO.getAccount().isEmpty())
        {
            throw new BaseException(UserConstant.ACCOUNT_EMPTY);
        }
        Password password = Password.builder()
                .userId(BaseContext.getCurrentId())
                .password(passwordDTO.getPassword())
                .account(passwordDTO.getAccount())
                .platformImage(passwordDTO.getPlatformImage())
                .platformName(passwordDTO.getPlatformName())
                .build();
        passwordMapper.insert(password);
    }

    @Override
    public void updatePassword(Password password) {
        passwordMapper.update(password);
    }

    @Override
    public List<Password> listAll(Long userId) {
        return passwordMapper.getByUserId(userId);
    }

    @Override
    public Password getById(Long id) {
        return passwordMapper.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        passwordMapper.deleteById(id);
    }

    @Override
    public void sendPassword(MessageDTO messageDTO) {
        Long userId = BaseContext.getCurrentId();
        log.info("发送者id:{},接收者id:{}",userId,messageDTO.getReceiverId());
        Message message = Message.builder()
                .senderId(userId)
                .receiverId(messageDTO.getReceiverId())
                .packageId(messageDTO.getPackageId())
                .packageCategory(UserConstant.PASSWORD_SEND)
                .build();
        messageMapper.insert(message);
    }

    @Transactional
    @Override
    public List<PasswordSendVO> listMessage(Long userId) {
        //1. 根据用户id和信息种类去信息表中查到信息类
        Message find = Message.builder()
                .receiverId(userId)
                .packageCategory(UserConstant.PASSWORD_SEND)
                .build();
        List<Message> messages = messageMapper.getMessage(find);
        if(messages == null || messages.isEmpty())
        {
            return null;
        }
        List<Long> messageIds = new ArrayList<>();
        List<PasswordSendVO> passwordSendVOS = new ArrayList<>();
        for (Message message : messages) {
            messageIds.add(message.getId());
            Friend friend = Friend.builder()
                    .userId(userId)
                    .friendId(message.getSenderId())
                    .build();
            Friend f = friendMapper.getByUserIdAndFriendId(friend);
            String remarkName = f.getRemarkName();
            User user = userMapper.getById(message.getSenderId());
            String image = user.getAvatar();
            Password password = passwordMapper.getById(message.getPackageId());
            PasswordSendVO passwordSendVO = PasswordSendVO.builder()
                    .packageId(message.getPackageId())
                    .account(password.getAccount())
                    .password(password.getPassword())
                    .image(image)
                    .remarkName(remarkName)
                    .platformName(password.getPlatformName())
                    .build();
            passwordSendVOS.add(passwordSendVO);
        }
        messageMapper.deleteMessages(messageIds);
        return passwordSendVOS;
    }

    @Override
    public void receivePassword(Long id) {
        //把对应的密码记录所属用户id改为自己的用户id
        Long userId = BaseContext.getCurrentId();
        log.info("{}用户接受密码{}",userId,id);
        Password password = Password.builder()
                .id(id)
                .userId(userId)
                .build();
        passwordMapper.update(password);
    }
}
