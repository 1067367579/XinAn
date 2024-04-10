package com.xinan.service.impl;

import com.xinan.constant.StatusConstant;
import com.xinan.constant.UserConstant;
import com.xinan.context.BaseContext;
import com.xinan.dto.MerchantAddressDTO;
import com.xinan.dto.ProcessDTO;
import com.xinan.entity.Process;
import com.xinan.entity.*;
import com.xinan.exception.BaseException;
import com.xinan.mapper.MerchantMapper;
import com.xinan.mapper.ProcessDetailMapper;
import com.xinan.mapper.ProcessMapper;
import com.xinan.service.ProcessService;
import com.xinan.vo.MerchantVO;
import com.xinan.vo.ProcessVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    private ProcessMapper processMapper;

    @Autowired
    private MerchantMapper merchantMapper;

    @Autowired
    private ProcessDetailMapper processDetailMapper;

    @Override
    public List<ProcessVO> getByUserId(Long userId) {
        List<Process> processes = processMapper.getByUserId(userId);
        List<ProcessVO> processVOs = new ArrayList<>();
        for (Process process : processes) {
            List<ProcessDetail> processDetail = processDetailMapper.getByProcessId(process.getId());
            ProcessVO processVO = ProcessVO.builder()
                    .id(process.getId())
                    .userId(process.getUserId())
                    .name(process.getName())
                    .status(process.getStatus())
                    .illustrate(process.getIllustrate())
                    .detail(processDetail)
                    .build();
            processVOs.add(processVO);
        }
        processVOs.sort(Comparator.comparingLong(ProcessVO::getId));
        return processVOs;
    }

    @Override
    public void addProcess(ProcessDTO processDTO) {
        Long userId = BaseContext.getCurrentId();
        Process process = Process.builder()
                .illustrate(processDTO.getIllustrate())
                .userId(userId)
                .name(processDTO.getName())
                .status(StatusConstant.NOT_FINISHED)
                .build();
        processMapper.insert(process);
    }

    @Transactional
    @Override
    public void deleteBatch(List<Long> ids) {
        processMapper.deleteBatch(ids);
        processDetailMapper.deleteBatchByProcessIds(ids);
    }

    @Override
    public void updateProcess(Process process) {
        if(process.getName() == null || process.getName().isEmpty())
        {
            throw new BaseException(UserConstant.PROCESS_NAME_EMPTY);
        }
        processMapper.update(process);
    }

    @Override
    public void addProcessDetail(ProcessDetail processDetail) {
        processDetailMapper.insert(processDetail);
    }

    @Override
    public void updateProcessDetail(ProcessDetail processDetail) {
        if(processDetail.getTitle() == null || processDetail.getTitle().isEmpty())
        {
            throw new BaseException(UserConstant.PROCESS_DETAIL_TITLE_EMPTY);
        }
        processDetailMapper.update(processDetail);
    }

    @Override
    public void deleteProcessDetailBatch(List<Long> ids) {
        if(ids == null || ids.isEmpty())
        {
            return;
        }
        processDetailMapper.deleteBatch(ids);
    }

    @Transactional
    @Override
    public void updateProcessDetailOrder(List<ProcessDetail> processDetails) {
        List<Long> ids = new ArrayList<>();
        processDetails.forEach(processDetail -> {
            ids.add(processDetail.getId());
        });
        processDetailMapper.deleteBatch(ids);
        processDetailMapper.insertBatch(processDetails);
    }

    @Override
    public List<MerchantVO> getMerchantByAddress(MerchantAddressDTO merchantAddressDTO) {
        //1. 在地址表中查询出符合条件的商户id
        List<Long> addressIds = merchantMapper.getMerchantByAddress(merchantAddressDTO);
        List<MerchantVO> merchantVOS = new ArrayList<>();
        //2. 再根据商户id集合到商户表中查询出商户信息返回
        for (Long addressId : addressIds) {
            Merchant merchant = merchantMapper.getByAddressId(addressId);
            if(merchant == null) continue;
            MerchantCategory category = merchantMapper.getCategoryById(merchant.getMerchantCategoryId());
            MerchantAddress address = merchantMapper.getAddressById(merchant.getMerchantAddressId());
            MerchantVO merchantVO = MerchantVO.builder()
                    .id(merchant.getId())
                    .phone(merchant.getPhone())
                    .leader(merchant.getLeader())
                    .merchantCategory(category.getName())
                    .merchantAddress(address.getCity()+address.getDistrict())
                    .name(merchant.getName())
                    .build();
            merchantVOS.add(merchantVO);
        }
        return merchantVOS;
    }

    @Transactional
    @Override
    public void updateProcessOrder(List<ProcessVO> processVOS) {
        List<Long> processIds = new ArrayList<>();
        for (ProcessVO processVO : processVOS) {
            processIds.add(processVO.getId());
        }
        processMapper.deleteBatch(processIds);
        processDetailMapper.deleteBatchByProcessIds(processIds);
        for (ProcessVO processVO : processVOS) {
            Process process = Process.builder()
                    .userId(processVO.getUserId())
                    .name(processVO.getName())
                    .status(processVO.getStatus())
                    .illustrate(processVO.getIllustrate())
                    .build();
            processMapper.insert(process);
            List<ProcessDetail> details = processVO.getDetail();
            if(details == null || details.isEmpty())
            {
                continue;
            }
            details.forEach(processDetail -> processDetail.setProcessId(process.getId()));
            processDetailMapper.insertBatch(details);
        }
    }


}
