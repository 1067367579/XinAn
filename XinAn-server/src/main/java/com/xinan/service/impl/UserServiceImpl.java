package com.xinan.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xinan.constant.MessageConstant;
import com.xinan.constant.StatusConstant;
import com.xinan.constant.UserConstant;
import com.xinan.constant.WeiXinConstant;
import com.xinan.context.BaseContext;
import com.xinan.dto.*;
import com.xinan.entity.Process;
import com.xinan.entity.*;
import com.xinan.exception.AccountNotFoundException;
import com.xinan.exception.BaseException;
import com.xinan.exception.LoginFailedException;
import com.xinan.mapper.*;
import com.xinan.properties.WeChatProperties;
import com.xinan.service.UserService;
import com.xinan.utils.HttpClientUtil;
import com.xinan.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    /**
     * 定义微信登录获取openid和session_key的接口地址
     */
    public static final String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";

    /**
     * 注入持久层对象
     */
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CheckListMapper checkListMapper;

    @Autowired
    private WishMapper wishMapper;

    @Autowired
    private FriendMapper friendMapper;

    @Autowired
    private ProcessMapper processMapper;

    @Autowired
    private FriendCategoryMapper friendCategoryMapper;

    @Autowired
    private MessageMapper messageMapper;

    /**
     * 注入微信配置项
     */
    @Autowired
    private WeChatProperties weChatProperties;

    @Autowired
    private MerchantMapper merchantMapper;

    /**
     * 登录逻辑
     * @param userLoginDTO 前端传过来的授权码
     * @return 用户对象
     */
    @Override
    @Transactional
    public User wxLogin(UserLoginDTO userLoginDTO) {

        //向微信服务器发送请求获取到openid和session key
        String openid = getOpenid(userLoginDTO.getCode());

        log.info("openid:{}",openid);

        if(openid == null){
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }

        //判断当前用户是否为新用户
        User user = userMapper.getByOpenid(openid);

        if(user == null)
        {
            //创建新用户,同时初始化数据
            String defaultUsername = UserConstant.DEFAULT_USERNAME_PREFIX+ UUID.randomUUID().toString().substring(0,5);
            user = User.builder()
                    .openid(openid)
                    .username(defaultUsername)
                    .phone(UserConstant.DEFAULT_PHONE)
                    .gender(UserConstant.DEFAULT_GENDER)
                    .birthday(LocalDate.now())
                    .avatar(UserConstant.DEFAULT_AVATAR)
                    .signature(UserConstant.DEFAULT_SIGNATURE)
                    .backgroundImage(UserConstant.DEFAULT_BACKGROUND_IMAGE)
                    .createTime(LocalDateTime.now())
                    .build();
            userMapper.insert(user);
            //同时要往需要增加记录的表中增加记录
            //1. 清单状态表 插入一条记录
            CheckList checkList = CheckList.builder()
                    .userId(user.getId())
                    .inviteState(StatusConstant.NOT_FINISHED)
                    .wishState(StatusConstant.NOT_FINISHED)
                    .socialPlatformState(StatusConstant.NOT_FINISHED)
                    .passwordBoxState(StatusConstant.NOT_FINISHED)
                    .petState(StatusConstant.NOT_FINISHED)
                    .treasureState(StatusConstant.NOT_FINISHED)
                    .build();
            checkListMapper.insertUser(checkList);
            //2. 服务流程表 插入多条记录
            List<Process> processes = getDefaultProcess(user.getId());
            //批量插入
            processMapper.insertBatch(processes);
            //3. 初始化分类 好友分类 葬礼邀请分类 初始化为我的好友
            friendCategoryMapper.insert(FriendCategory.builder()
                    .userId(user.getId())
                    .name("我的好友").build());
        }
        return user;
    }

    /**
     * 获取默认的服务流程表
     * @param id 用户id
     * @return 默认服务流程表
     */
    public List<Process> getDefaultProcess(Long id)
    {
        List<Process> processes = new ArrayList<>();
        processes.add(Process.builder()
                        .userId(id)
                        .name("医生确认死亡")
                        .illustrate("这里写点什么")
                        .status(StatusConstant.NOT_FINISHED)
                        .order(1).build());
        processes.add(Process.builder()
                        .userId(id)
                        .name("净身穿衣")
                        .illustrate("携带相关证件")
                        .status(StatusConstant.NOT_FINISHED)
                        .order(2).build());
        processes.add(Process.builder()
                        .userId(id)
                        .name("开具死亡证明")
                        .illustrate("携带证件")
                        .status(StatusConstant.NOT_FINISHED)
                        .order(3).build());
        processes.add(Process.builder()
                        .userId(id)
                        .name("联系殡仪馆灵车")
                        .illustrate("灵车运送,存放遗体")
                        .status(StatusConstant.NOT_FINISHED)
                        .order(4).build());
        processes.add(Process.builder()
                        .userId(id)
                        .name("搭设灵堂")
                        .illustrate("多种品类选择")
                        .status(StatusConstant.NOT_FINISHED)
                        .order(5).build());
        processes.add(Process.builder()
                        .userId(id)
                        .name("预约殡仪馆事项")
                        .illustrate("这里写点什么")
                        .status(StatusConstant.NOT_FINISHED)
                        .order(6).build());
        processes.add(Process.builder()
                        .userId(id)
                        .name("选购殡葬用品")
                        .illustrate("多种品类选择")
                        .status(StatusConstant.NOT_FINISHED)
                        .order(7).build());
        processes.add(Process.builder()
                        .userId(id)
                        .name("预备丧宴")
                        .illustrate("提前预约")
                        .status(StatusConstant.NOT_FINISHED)
                        .order(8).build());
        processes.add(Process.builder()
                        .userId(id)
                        .name("办理殡仪馆手续")
                        .illustrate("携带相关证件")
                        .status(StatusConstant.NOT_FINISHED)
                        .order(9).build());
        processes.add(Process.builder()
                        .userId(id)
                        .name("出殡仪式")
                        .illustrate("这里写点什么")
                        .status(StatusConstant.NOT_FINISHED)
                        .order(10).build());
        processes.add(Process.builder()
                        .userId(id)
                        .name("举行尊体告别仪式")
                        .illustrate("这里写点什么")
                        .status(StatusConstant.NOT_FINISHED)
                        .order(11).build());
        processes.add(Process.builder()
                        .userId(id)
                        .name("尊体火化")
                        .illustrate("骨灰寄存/安葬")
                        .status(StatusConstant.NOT_FINISHED)
                        .order(12).build());
        processes.add(Process.builder()
                        .userId(id)
                        .name("后续祭祀")
                        .illustrate("当地特殊习俗")
                        .status(StatusConstant.NOT_FINISHED)
                        .order(13).build());
        return processes;
    }

    /**
     * 根据用户id查询用户基本信息 及需要计算的数据
     * @param id 用户id
     * @return VO
     */
    @Override
    public UserVO getById(Long id) {
        //从用户表中获取用户基本信息
        User user = userMapper.getById(id);
        //返回VO对象
        return UserVO.builder()
                .id(id)
                .username(user.getUsername())
                .phone(user.getPhone())
                .signature(user.getSignature())
                .gender(user.getGender())
                .avatar(user.getAvatar())
                .birthday(user.getBirthday())
                .backgroundImage(user.getBackgroundImage())
                .build();
    }

    @Override
    public Long getDayToBirthday(Long id) {
        User user = userMapper.getById(id);
        LocalDate birthday = user.getBirthday();
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = birthday.getMonthValue();
        int day = birthday.getDayOfMonth();
        int birthdayOfYear = birthday.getDayOfYear();
        int todayOfYear = today.getDayOfYear();
        LocalDate latestBirthday;
        if(todayOfYear>birthdayOfYear)
        {
            //今年的生日已经过了 最早的是明年
            latestBirthday = LocalDate.of(year+1,month,day);
        }
        else
        {
            latestBirthday = LocalDate.of(year,month,day);
        }
        long days = today.until(latestBirthday, ChronoUnit.DAYS);
        log.info("还有{}天到生日",days);
        return days;
    }

    /**
     * 更新头像
     * @param userDTO 用户传输数据模型
     */
    @Override
    public void updateUser(UserDTO userDTO, Method invoker) {
        User user = null;
        switch (invoker.getName()) {
            case "updateAvatar" -> user = User.builder()
                    .id(userDTO.getId())
                    .avatar(userDTO.getAvatar())
                    .build();
            case "updateBackgroundImage" -> user = User.builder()
                    .id(userDTO.getId())
                    .backgroundImage(userDTO.getBackgroundImage())
                    .build();
            case "updateUser" -> {
                //手机号重复性校验
                User tmp = null;
                if ((tmp = userMapper.getByPhone(userDTO.getPhone())) != null &&
                    !tmp.getId().equals(userDTO.getId())) {
                    throw new BaseException(UserConstant.DUPLICATE_PHONE);
                }
                //手机号码合法性校验
                if (userDTO.getPhone() != null) {
                    if (!userDTO.getPhone().matches("\\d{11}")) {
                        throw new BaseException(MessageConstant.WRONG_PHONE);
                    }
                }
                user = User.builder()
                        .id(userDTO.getId())
                        .avatar(userDTO.getAvatar())
                        .backgroundImage(userDTO.getBackgroundImage())
                        .birthday(userDTO.getBirthday())
                        .gender(userDTO.getGender())
                        .phone(userDTO.getPhone())
                        .signature(userDTO.getSignature())
                        .username(userDTO.getUsername())
                        .build();
            }
        }
        userMapper.update(user);
    }

    /**
     * 将该用户所有好友查询出来 以VO集合的形式返回给前端
     * @param id
     * @return
     */
    @Override
    public List<FriendVO> listFriends(Long id) {
        //直接使用多表联查
        return friendMapper.listFriends(id);
    }

    @Override
    public UserVO getUserByPhone(String phone) {
        User user = userMapper.getByPhone(phone);
        if(user == null)
        {
            //找不到用户 返回错误信息
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND_ERROR);
        }
        Long id = user.getId();
        //获取当前用户对应的清单状态条目对象
        CheckList checkList = checkListMapper.getByUserId(id);
        //计算已经完成的项目数
        Integer finishCount = getFinishedCount(checkList);
        //计算清单完成进度
        double listProcess = finishCount.doubleValue()/UserConstant.CHECKLIST_COUNT*100;
        //获取当前用户愿望清单中的愿望个数
        Integer wishCount = wishMapper.getCountByUserId(id);
        //获取当前用户好友个数
        Integer friendCount = friendMapper.getCountByUserId(id);
        //返回VO对象
        return UserVO.builder()
                .id(id)
                .username(user.getUsername())
                .phone(user.getPhone())
                .signature(user.getSignature())
                .gender(user.getGender())
                .avatar(user.getAvatar())
                .birthday(user.getBirthday())
                .backgroundImage(user.getBackgroundImage())
                .listProcess((int) listProcess)
                .wishCount(wishCount)
                .friendCount(friendCount)
                .build();
    }

    /**
     * 添加好友操作
     * @param friendDTO 好友数据传输模型
     */
    @Override
    @Transactional
    public void addFriend(FriendDTO friendDTO) {
        Long userId = BaseContext.getCurrentId();
        Long friendId = friendDTO.getId();
        Long friendCategoryId = friendDTO.getFriendCategoryId();
        String remarkName = friendDTO.getRemarkName();
        User friend = userMapper.getById(friendId);
        User user = userMapper.getById(userId);
        if(remarkName.isEmpty())
        {
            //如果备注名为空 那么默认初始化为用户名
            remarkName = friend.getUsername();
        }
        LocalDateTime createTime = LocalDateTime.now();
        friendMapper.insert(Friend.builder()
                .userId(userId)
                .friendId(friendId)
                .remarkName(remarkName)
                .friendCategoryId(friendCategoryId)
                .createTime(createTime)
                //表示未邀请该好友
                .invited(StatusConstant.NOT_FINISHED)
                .build());
        remarkName = user.getUsername();
        friendMapper.insert(Friend.builder()
                .userId(friendId)
                .friendId(userId)
                .remarkName(remarkName)
                .friendCategoryId(friendCategoryId)
                .createTime(createTime)
                //表示未邀请该好友
                .invited(StatusConstant.NOT_FINISHED)
                .build());
    }

    @Override
    public UserVO getCardById(Long id) {
        User user = userMapper.getById(id);
        //获取当前用户对应的清单状态条目对象
        CheckList checkList = checkListMapper.getByUserId(id);
        //计算已经完成的项目数
        Integer finishCount = getFinishedCount(checkList);
        //计算清单完成进度
        double listProcess = finishCount.doubleValue()/UserConstant.CHECKLIST_COUNT*100;
        //获取当前用户愿望清单中的愿望个数
        Integer wishCount = wishMapper.getCountByUserId(id);
        //获取当前用户好友个数
        Integer friendCount = friendMapper.getCountByUserId(id);
        //返回VO对象
        return UserVO.builder()
                .id(id)
                .username(user.getUsername())
                .phone(user.getPhone())
                .birthday(user.getBirthday())
                .signature(user.getSignature())
                .gender(user.getGender())
                .avatar(user.getAvatar())
                .backgroundImage(user.getBackgroundImage())
                .listProcess((int) listProcess)
                .wishCount(wishCount)
                .friendCount(friendCount)
                .build();
    }

    /**
     * 更新好友信息
     * @param friendDTO 好友信息
     */
    @Override
    public void updateFriend(FriendDTO friendDTO) {
        Friend friend = Friend.builder()
                .userId(BaseContext.getCurrentId())
                .friendId(friendDTO.getId())
                .remarkName(friendDTO.getRemarkName())
                .invited(friendDTO.getInvited())
                .build();
        friendMapper.update(friend);
    }

    @Override
    public void insertFriendCategory(FriendCategoryDTO friendCategoryDTO) {
        //要检查是否有重复 (还要是相同的用户)
        Long userId = BaseContext.getCurrentId();
        List<FriendCategory> categories = friendCategoryMapper.getByUserId(userId);
        String name = friendCategoryDTO.getName();
        for (FriendCategory category : categories) {
            if(category.getName().equals(name))
            {
                throw new BaseException(MessageConstant.DUPLICATE_CATEGORY);
            }
        }
        //构建实体对象插入
        FriendCategory friendCategory = FriendCategory.builder()
                .userId(userId)
                .name(name)
                .build();
        friendCategoryMapper.insert(friendCategory);
    }

    @Override
    public List<FriendCategoryVO> getFriendCategories(Long id) {
        List<FriendCategory> categories = friendCategoryMapper.getByUserId(id);
        List<FriendCategoryVO> vo = new ArrayList<>();
        for (FriendCategory category : categories) {
            vo.add(FriendCategoryVO.builder()
                            .name(category.getName())
                            .id(category.getId())
                            .build());
        }
        return vo;
    }

    @Override
    public List<MerchantVO> getFavorites(Long id) {
        List<MerchantVO> list = new ArrayList<>();
        //获取当前用户所收藏的商户id
        List<Long> ids = merchantMapper.listFavoritesIds(id);
        for (Long merchantId : ids) {
            Merchant merchant = merchantMapper.getById(merchantId);
            Long addressId = merchant.getMerchantAddressId();
            Long categoryId = merchant.getMerchantCategoryId();
            MerchantAddress address = merchantMapper.getAddressById(addressId);
            MerchantCategory category = merchantMapper.getCategoryById(categoryId);
            list.add(MerchantVO.builder()
                            .id(merchant.getId())
                            .name(merchant.getName())
                            .merchantAddress(address.getCity()+address.getDistrict()+address.getDetail())
                            .merchantCategory(category.getName())
                            .leader(merchant.getLeader())
                            .phone(merchant.getPhone())
                    .build());
        }
        return list;
    }

    @Override
    public void addFavorite(UserMerchantDTO userMerchantDTO) {
        Long userId = BaseContext.getCurrentId();
        LocalDateTime createTime = LocalDateTime.now();
        Long merchantId = userMerchantDTO.getMerchantId();
        UserMerchant userMerchant = UserMerchant.builder()
                .userId(userId)
                .merchantId(merchantId)
                .createTime(createTime)
                .build();
        merchantMapper.addFavorite(userMerchant);
    }

    @Override
    public void updateFriendCategory(FriendCategory friendCategory) {
        friendCategoryMapper.update(friendCategory);
    }

    @Override
    public void deleteFriendCategoryById(Long id) {
        FriendCategory category = friendCategoryMapper.getById(id);
        if(category.getName().equals("我的好友"))
        {
            throw new BaseException(MessageConstant.DELETE_NOT_ALLOWED);
        }
        friendCategoryMapper.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteFriendById(Long friendId) {
        Long userId = BaseContext.getCurrentId();
        Friend friend = Friend.builder()
                        .userId(userId)
                        .friendId(friendId)
                        .build();
        //根据自己的id和好友的id定位记录进行删除
        friendMapper.deleteFriend(friend);
        Friend friend1 = Friend.builder()
                .userId(friendId)
                .friendId(userId)
                .build();
        friendMapper.deleteFriend(friend1);
    }

    /**
     * 发送好友请求 添加进信息表
     * @param messageDTO 信息数据传输模型
     */
    @Override
    public void sendRequest(MessageDTO messageDTO) {
        Long senderId = BaseContext.getCurrentId();
        Integer packageCategory = UserConstant.FRIEND_REQUEST;
        Long receiverId = messageDTO.getReceiverId();
        Friend friend = Friend.builder()
                .userId(senderId)
                .friendId(receiverId)
                .build();
        Friend res = friendMapper.getByUserIdAndFriendId(friend);
        if(res!=null)
        {
            throw new BaseException(MessageConstant.ALREADY_FRIEND);
        }
        Message message = Message.builder()
                .senderId(senderId)
                .receiverId(receiverId)
                .packageCategory(packageCategory)
                .build();
        messageMapper.insert(message);
    }

    @Override
    public List<FriendRequestVO> listMessage(Long userId) {
        Message message = Message.builder()
                .receiverId(userId)
                .packageCategory(UserConstant.FRIEND_REQUEST)
                .build();
        //1. 根据用户id和信息种类去信息表中查到信息类
        List<Message> messages = messageMapper.getMessage(message);
        if(messages.isEmpty())
        {
            return null;
        }
        List<Long> ids = new ArrayList<>();
        List<Long> messageIds = new ArrayList<>();
        for (Message m : messages) {
            ids.add(m.getSenderId());
            messageIds.add(m.getId());
        }
        //要删除这些信息
        messageMapper.deleteMessages(messageIds);
        //2. 再根据用户id集合到用户表中查询到信息返回
        return userMapper.getRequest(ids);
    }


    /**
     * 获取清单状态中已经完成的项目的个数
     * @param checkList 清单状态对象
     * @return 完成个数
     */
    public Integer getFinishedCount(CheckList checkList) {
        Integer count = 0;
        if(checkList.getInviteState().equals(StatusConstant.FINISHED))
        {
            count++;
        }
        if(checkList.getWishState().equals(StatusConstant.FINISHED))
        {
            count++;
        }
        if(checkList.getSocialPlatformState().equals(StatusConstant.FINISHED))
        {
            count++;
        }
        if(checkList.getPasswordBoxState().equals(StatusConstant.FINISHED))
        {
            count++;
        }
        if(checkList.getPetState().equals(StatusConstant.FINISHED))
        {
            count++;
        }
        if(checkList.getTreasureState().equals(StatusConstant.FINISHED))
        {
            count++;
        }
        return count;
    }

    /**
     * 通过session_key,encryptedData,iv解析用户数据
     */
    public static String decryptUserInfo(String encryptedData, String sessionKey, String iv) {
        try {
            byte[] encryptedDataBytes = Base64.getDecoder().decode(encryptedData);
            byte[] sessionKeyBytes = Base64.getDecoder().decode(sessionKey);
            byte[] ivBytes = Base64.getDecoder().decode(iv);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec secretKeySpec = new SecretKeySpec(sessionKeyBytes, "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

            byte[] decryptedData = cipher.doFinal(encryptedDataBytes);
            return new String(decryptedData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 调用微信接口服务 获取微信用户openid和session_key并返回
     * @param code 授权码
     * @return openid
     */
    public String getOpenid(String code)
    {
        Map<String,String> map = new HashMap<>();
        map.put(WeiXinConstant.APP_ID,weChatProperties.getAppid());
        map.put(WeiXinConstant.SECRET,weChatProperties.getSecret());
        map.put(WeiXinConstant.JS_CODE,code);
        map.put(WeiXinConstant.GRANT_TYPE,WeiXinConstant.AUTHORIZATION_CODE);
        String response = HttpClientUtil.doGet(WX_LOGIN,map);
        JSONObject jsonObject = JSON.parseObject(response);
        return jsonObject.getString(WeiXinConstant.OPEN_ID);
    }
}
