package com.xinan.service.impl;

import com.xinan.context.BaseContext;
import com.xinan.dto.MomentDTO;
import com.xinan.dto.MomentLikesDTO;
import com.xinan.entity.Moment;
import com.xinan.entity.MomentLikes;
import com.xinan.entity.User;
import com.xinan.mapper.MomentMapper;
import com.xinan.mapper.UserMapper;
import com.xinan.service.MomentService;
import com.xinan.vo.MomentLikesVO;
import com.xinan.vo.MomentVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MomentServiceImpl implements MomentService {

    @Autowired
    private MomentMapper momentMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 添加新的安心圈动态
     * @param momentDTO 安心圈动态数据传输模型
     */
    @Override
    public void insertMoment(MomentDTO momentDTO) {
        Moment moment = new Moment();
        BeanUtils.copyProperties(momentDTO,moment);
        moment.setCreateTime(LocalDateTime.now());
        if(momentDTO.getId() == null)
        {
            moment.setUserId(BaseContext.getCurrentId());
        }
        momentMapper.insertMoment(moment);
    }

    @Override
    public void updateMoment(MomentDTO momentDTO) {
        Moment moment = new Moment();
        BeanUtils.copyProperties(momentDTO,moment);
        moment.setCreateTime(LocalDateTime.now());
        momentMapper.updateMoment(moment);
    }

    @Override
    public void insertMomentLikes(MomentLikesDTO momentLikesDTO) {
        MomentLikes momentLikes = new MomentLikes();
        BeanUtils.copyProperties(momentLikesDTO,momentLikes);
        momentLikes.setCreateTime(LocalDateTime.now());
        momentMapper.insertLikes(momentLikes);
    }

    @Override
    public void removeMomentLikes(Long id) {
        momentMapper.deleteLikesById(id);
    }

    @Override
    public List<MomentVO> listAll() {
        //先将所有的Moment对象查询出来
        List<Moment> momentList = momentMapper.listAll();
        return getMomentVOS(momentList);
    }

    private List<MomentVO> getMomentVOS(List<Moment> momentList) {
        List<MomentVO> vos = new ArrayList<>();
        //根据逻辑外键将相应的内容查出来 放入VO
        for (Moment moment : momentList) {
            Long momentId = moment.getId();
            Long userId = moment.getUserId();
            //根据用户id把相应的内容查出来
            User user = userMapper.getById(userId);
            String avatar = user.getAvatar();
            //根据安心圈动态id查点赞
            List<MomentLikesVO> likeVOs = new ArrayList<>();
            List<MomentLikes> likes = momentMapper.getLikesByMomentId(momentId);
            for (MomentLikes like : likes) {
                Long id = like.getUserId();
                //到用户表中查
                String username = userMapper.getById(id).getUsername();
                likeVOs.add(MomentLikesVO.builder()
                                .id(id)
                                .username(username)
                                .build());
            }
            vos.add(MomentVO.builder()
                            .id(momentId)
                            .avatar(avatar)
                            .title(moment.getTitle())
                            .userId(userId)
                            .username(user.getUsername())
                            .subTitle(moment.getSubTitle())
                            .photo1(moment.getPhoto1())
                            .photo2(moment.getPhoto2())
                            .photo3(moment.getPhoto3())
                            .photo4(moment.getPhoto4())
                            .beginDate(moment.getBeginDate())
                            .endDate(moment.getEndDate())
                            .momentLikes(likeVOs)
                    .build());
        }
        return vos;
    }

    @Override
    public List<MomentVO> getMomentsByUserId(Long userId) {
        //先将所有的Moment对象查询出来
        List<Moment> momentList = momentMapper.getByUserId(userId);
        return getMomentVOS(momentList);
    }

    @Override
    public void deleteById(Long id) {
        momentMapper.deleteById(id);
    }


}
