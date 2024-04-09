package com.xinan.service.impl;

import com.xinan.constant.UserConstant;
import com.xinan.context.BaseContext;
import com.xinan.dto.MessageDTO;
import com.xinan.dto.PetDTO;
import com.xinan.entity.Friend;
import com.xinan.entity.Message;
import com.xinan.entity.Pet;
import com.xinan.entity.User;
import com.xinan.exception.BaseException;
import com.xinan.mapper.FriendMapper;
import com.xinan.mapper.MessageMapper;
import com.xinan.mapper.PetMapper;
import com.xinan.mapper.UserMapper;
import com.xinan.service.PetService;
import com.xinan.vo.PetSendVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PetServiceImpl implements PetService {

    @Autowired
    private PetMapper petMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private FriendMapper friendMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void addPet(PetDTO petDTO) {
        Long userId = BaseContext.getCurrentId();
        if(petDTO.getName() == null ||
        petDTO.getName().isEmpty())
        {
            throw new BaseException(UserConstant.PET_NAME_EMPTY);
        }
        log.info("用户{}添加宠物",userId);
        Pet pet = Pet.builder()
                .userId(userId)
                .image(petDTO.getImage())
                .name(petDTO.getName())
                .build();
        petMapper.insert(pet);
    }

    @Override
    public List<Pet> listAll(Long userId) {
        return petMapper.getByUserId(userId);
    }

    @Override
    public Pet getById(Long id) {
        return petMapper.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        petMapper.deleteById(id);
    }

    @Override
    public void update(Pet pet) {
        if(pet.getName() == null || pet.getName().isEmpty())
        {
            throw new BaseException(UserConstant.PET_NAME_EMPTY);
        }
        petMapper.update(pet);
    }

    @Override
    public void sendPet(MessageDTO messageDTO) {
        Long userId = BaseContext.getCurrentId();
        log.info("当前用户id:{}",userId);
        Message message = Message.builder()
                .receiverId(messageDTO.getReceiverId())
                .packageCategory(UserConstant.PET_SEND)
                .packageId(messageDTO.getPackageId())
                .senderId(userId)
                .build();
        messageMapper.insert(message);
    }

    @Transactional
    @Override
    public List<PetSendVO> listMessage(Long userId) {
        //1. 先根据收件人id在信息表中查询到相关信息
        Message find = Message.builder()
                .packageCategory(UserConstant.PET_SEND)
                .receiverId(userId)
                .build();
        List<Message> messages = messageMapper.getMessage(find);
        if(messages == null || messages.isEmpty())
        {
            return null;
        }
        List<Long> messageIds = new ArrayList<>();
        List<PetSendVO> petSendVOs = new ArrayList<>();
        //2. 在好友表 用户表 宠物表中查询出数据封装成vo
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
            Pet pet = petMapper.getById(message.getPackageId());
            PetSendVO petSendVO = PetSendVO.builder()
                    .petId(pet.getId())
                    .petName(pet.getName())
                    .petImage(pet.getImage())
                    .image(image)
                    .remarkName(remarkName)
                    .build();
            petSendVOs.add(petSendVO);
        }
        //3. 删除查询到的信息
        messageMapper.deleteMessages(messageIds);
        return petSendVOs;
    }

    @Override
    public void receivePet(Long id) {
        Long userId = BaseContext.getCurrentId();
        Pet pet = Pet.builder()
                .userId(userId)
                .id(id)
                .build();
        petMapper.update(pet);
    }
}
