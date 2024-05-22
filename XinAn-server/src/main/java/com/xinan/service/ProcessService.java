package com.xinan.service;

import com.xinan.dto.MerchantAddressDTO;
import com.xinan.dto.ProcessDTO;
import com.xinan.entity.Merchant;
import com.xinan.entity.Process;
import com.xinan.entity.ProcessDetail;
import com.xinan.vo.MerchantVO;
import com.xinan.vo.ProcessVO;

import java.util.List;

public interface ProcessService {
    List<ProcessVO> getByUserId(Long userId);

    void addProcess(ProcessDTO processDTO);

    void deleteBatch(List<Long> ids);

    void updateProcess(Process process);

    void addProcessDetail(ProcessDetail processDetail);

    void updateProcessDetail(ProcessDetail processDetail);

    void deleteProcessDetailBatch(List<Long> ids);

    void updateProcessDetailOrder(List<ProcessDetail> processDetails);

    List<MerchantVO> getMerchantByAddress(MerchantAddressDTO merchantAddressDTO);

    void updateProcessOrder(List<ProcessVO> processVOS);

    void addMerchant(Merchant merchant);

    void updateMerchant(Merchant merchant);

    void deleteMerchant(Long id);
}
