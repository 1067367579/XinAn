package com.xinan.service;

import com.xinan.dto.MessageDTO;
import com.xinan.dto.PetDTO;
import com.xinan.entity.Pet;
import com.xinan.vo.PetSendVO;

import java.util.List;

public interface PetService {
    void addPet(PetDTO petDTO);

    List<Pet> listAll(Long userId);

    Pet getById(Long id);

    void deleteById(Long id);

    void update(Pet pet);

    void sendPet(MessageDTO messageDTO);

    List<PetSendVO> listMessage(Long userId);

    void receivePet(Long id);
}
