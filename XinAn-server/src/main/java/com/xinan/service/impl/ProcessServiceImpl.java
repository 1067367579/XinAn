package com.xinan.service.impl;

import com.xinan.constant.StatusConstant;
import com.xinan.constant.UserConstant;
import com.xinan.context.BaseContext;
import com.xinan.dto.MerchantAddressDTO;
import com.xinan.dto.ProcessDTO;
import com.xinan.entity.Merchant;
import com.xinan.entity.MerchantCategory;
import com.xinan.entity.Process;
import com.xinan.entity.ProcessDetail;
import com.xinan.exception.BaseException;
import com.xinan.mapper.MerchantMapper;
import com.xinan.mapper.ProcessDetailMapper;
import com.xinan.mapper.ProcessMapper;
import com.xinan.service.ProcessService;
import com.xinan.utils.BaiduMapUtil;
import com.xinan.utils.BaiduMapUtil.Point;
import com.xinan.vo.MerchantVO;
import com.xinan.vo.ProcessVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    private ProcessMapper processMapper;

    @Autowired
    private MerchantMapper merchantMapper;

    @Autowired
    private ProcessDetailMapper processDetailMapper;

    @Autowired
    private BaiduMapUtil baiduMapUtil;

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
    @Transactional
    public List<MerchantVO> getMerchantByAddress(MerchantAddressDTO merchantAddressDTO) {
        //1. 查询出符合响应地址条件的商户
        List<Merchant> merchants = merchantMapper.getMerchantByAddress(merchantAddressDTO);
        //如果符合条件的商户列表为空 直接返回null
        if(merchants == null || merchants.isEmpty())
        {
            return null;
        }

        //用户所在坐标
        Point userPoint = new Point(merchantAddressDTO.getLat(), merchantAddressDTO.getLng());

        // 获取商户分类和地址信息并行处理 利用并行流进行集合处理
        return merchants.parallelStream().map(merchant -> {
            // 获取分类信息
            MerchantCategory category = merchantMapper.getCategoryById(merchant.getMerchantCategoryId());

            // 拼接地址字符串
            String addressStr = merchant.getCity() + merchant.getDistrict() + merchant.getDetail();

            //算好用户与该商家的距离,如果用户给了定位权限,计算距离
            Point merchantPoint = new Point(merchant.getLat(), merchant.getLng());

            Integer distance = 0;
            if(merchantAddressDTO.getLat()!=null && merchantAddressDTO.getLng()!=null)
            {
                distance = baiduMapUtil.getDistance(userPoint, merchantPoint);
            }

            // 返回商户视图对象
            return MerchantVO.builder()
                    .id(merchant.getId())
                    .phone(merchant.getPhone())
                    .leader(merchant.getLeader())
                    .merchantCategory(category.getName())
                    .merchantAddress(addressStr)
                    .name(merchant.getName())
                    .distance(distance)
                    .build();
        }).sorted(new Comparator<MerchantVO>() {
            @Override
            public int compare(MerchantVO o1, MerchantVO o2) {
                return o1.getDistance() - o2.getDistance();
            }
        }).collect(Collectors.toList());
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

    @Override
    public void addMerchant(Merchant merchant) {
        //通过地址信息获取坐标
        String address = merchant.getCity() + merchant.getDistrict() + merchant.getDetail();
        Point point = baiduMapUtil.getPointByAddress(address);
        merchant.setLat(point.getLat());
        merchant.setLng(point.getLng());
        merchantMapper.addMerchant(merchant);
    }

    @Override
    public void updateMerchant(Merchant merchant) {
        //通过地址信息获取坐标
        String address = merchant.getCity() + merchant.getDistrict() + merchant.getDetail();
        Point point = baiduMapUtil.getPointByAddress(address);
        merchant.setLat(point.getLat());
        merchant.setLng(point.getLng());
        merchantMapper.updateMerchant(merchant);
    }

    public void deleteMerchant(Long merchantId)
    {
        merchantMapper.deleteMerchant(merchantId);
    }

    @Override
    public void addAllMerchant() {
        List<Merchant> merchants = baiduMapUtil.getMerchants();
        merchantMapper.insertBatch(merchants);
    }
}
