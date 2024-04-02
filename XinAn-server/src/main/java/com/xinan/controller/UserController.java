package com.xinan.controller;

import com.xinan.constant.JwtClaimsConstant;
import com.xinan.context.BaseContext;
import com.xinan.dto.*;
import com.xinan.entity.FriendCategory;
import com.xinan.entity.User;
import com.xinan.properties.JwtProperties;
import com.xinan.result.Result;
import com.xinan.service.UserService;
import com.xinan.utils.JwtUtil;
import com.xinan.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@Api(tags = "用户模块相关接口")
@RequestMapping("/user/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/login")
    @ApiOperation("微信登录接口")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO)
    {
        log.info("微信登录授权码:{}",userLoginDTO.getCode());

        User user = userService.wxLogin(userLoginDTO);

        //生成令牌
        Map<String,Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID,user.getId());
        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);

        //封装返回的VO
        UserLoginVO vo = UserLoginVO.builder()
                .id(user.getId())
                .openid(user.getOpenid())
                .token(token)
                .build();

        return Result.success(vo);
    }

    @GetMapping("/card/{id}")
    @ApiOperation(value = "根据用户id查询用户名片")
    public Result<UserVO> getCardById(@PathVariable Long id)
    {
        log.info("根据id查询用户名片:{}",id);
        UserVO vo = userService.getCardById(id);
        return Result.success(vo);
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "根据用户id查询用户详细信息")
    public Result<UserVO> getById(@PathVariable Long id)
    {
        log.info("根据id查询用户信息");

        UserVO vo = userService.getById(id);

        return Result.success(vo);
    }

    @GetMapping("/birthday/{id}")
    @ApiOperation(value = "根据用户id查询距离用户最近生日天数")
    public Result<Long> getDayToBirthday(@PathVariable Long id)
    {
        log.info("根据id:{} 查询还有多少天到用户生日",id);
        Long days = userService.getDayToBirthday(id);
        return Result.success(days);
    }

    @PutMapping("/avatar")
    @ApiOperation(value = "修改用户头像")
    public Result updateAvatar(@RequestBody UserDTO userDTO)
    {
        log.info("修改用户头像:{}",userDTO.getAvatar());
        userService.updateUser(userDTO);
        return Result.success();
    }

    @PutMapping("/backgroundImage")
    @ApiOperation(value = "修改用户页背景图片")
    public Result updateBackgroundImage(@RequestBody UserDTO userDTO)
    {
        log.info("修改用户背景图片:{}",userDTO.getBackgroundImage());
        userService.updateUser(userDTO);
        return Result.success();
    }

    @PutMapping
    @ApiOperation(value = "修改用户基本信息")
    public Result updateUser(@RequestBody UserDTO userDTO)
    {
        log.info("修改用户基本信息:{}",userDTO);
        userService.updateUser(userDTO);
        return Result.success();
    }

    @GetMapping("/friends")
    @ApiOperation(value = "查找用户所有好友")
    public Result<List<FriendVO>> getAllFriends()
    {
        Long id = BaseContext.getCurrentId();
        log.info("根据id查询用户所有好友:{}",id);
        List<FriendVO> list = userService.listFriends(id);
        return Result.success(list);
    }

    @PostMapping("/friends/request")
    @ApiOperation(value = "发送好友请求")
    public Result sendRequest(@RequestBody MessageDTO messageDTO)
    {
        log.info("发送添加好友:{} 的请求",messageDTO.getReceiverId());
        userService.sendRequest(messageDTO);
        return Result.success();
    }

    //TODO 查看当前用户好友请求
    @GetMapping("/friends/request")
    @ApiOperation(value = "查看好友请求")
    public Result<List<FriendRequestVO>> listMessage()
    {
        log.info("查看当前用户好友请求");
        return Result.success();
    }

    @PostMapping("/friends")
    @ApiOperation(value = "添加好友")
    public Result addFriend(@RequestBody FriendDTO friendDTO)
    {
        log.info("添加好友:{}",friendDTO);
        userService.addFriend(friendDTO);
        return Result.success();
    }

    @GetMapping("/find/{phone}")
    @ApiOperation(value = "根据手机号查询用户")
    public Result<UserVO> getUserByPhone(@PathVariable String phone)
    {
        log.info("根据手机号查询用户:{}",phone);
        UserVO vo = userService.getUserByPhone(phone);
        return Result.success(vo);
    }

    @PutMapping("/friends")
    @ApiOperation(value = "更改好友信息")
    public Result updateFriend(@RequestBody FriendDTO friendDTO)
    {
        log.info("更改好友信息:{}",friendDTO);
        userService.updateFriend(friendDTO);
        return Result.success();
    }

    @PostMapping("/friendCategory")
    @ApiOperation(value = "插入新的好友分类")
    public Result addFriendCategory(@RequestBody FriendCategoryDTO friendCategoryDTO)
    {
        log.info("插入新分类:{}",friendCategoryDTO);
        userService.insertFriendCategory(friendCategoryDTO);
        return Result.success();
    }

    @GetMapping("/friendCategory")
    @ApiOperation(value = "查询当前用户好友分类")
    public Result<List<FriendCategoryVO>> getByUserId()
    {
        Long id = BaseContext.getCurrentId();
        log.info("根据用户id查看好友分类:{}",id);
        List<FriendCategoryVO> list = userService.getFriendCategories(id);
        return Result.success(list);
    }

    @GetMapping("/favorites")
    @ApiOperation(value = "获取当前用户收藏的商家")
    public Result<List<MerchantVO>> getFavorites()
    {
        Long id = BaseContext.getCurrentId();
        log.info("获取当前用户收藏的商家,当前用户:{}",id);
        List<MerchantVO> list = userService.getFavorites(id);
        return Result.success(list);
    }

    @PostMapping("/favourites")
    @ApiOperation(value = "用户根据商家id收藏商家")
    public Result addFavourite(@RequestBody UserMerchantDTO userMerchantDTO)
    {
        log.info("要收藏的商家:{}",userMerchantDTO.getMerchantId());
        userService.addFavorite(userMerchantDTO);
        return Result.success();
    }

    //更改好友分组信息
    @PutMapping("/friendCategory")
    @ApiOperation(value = "根据好友分组对象id修改分组信息")
    public Result updateFriendCategory(@RequestBody FriendCategory friendCategory)
    {
        log.info("根据好友分组对象id修改分组信息:{}",friendCategory.getId());
        userService.updateFriendCategory(friendCategory);
        return Result.success();
    }

    //删除分组时 要将所有该组的好友移到未分组(我的好友)的组中
    // 我的好友分组不可删除
    @DeleteMapping("/friendCategory/{id}")
    @ApiOperation(value = "根据好友分组信息id删除分组")
    public Result deleteFriendCategoryById(@PathVariable Long id)
    {
        log.info("根据好友分组信息id删除分组:{}",id);
        userService.deleteFriendCategoryById(id);
        return Result.success();
    }

    //删除好友 直接就把好友记录表的记录删除即可
    @DeleteMapping("/friend/{friendId}")
    @ApiOperation(value = "根据好友ID删除好友")
    public Result deleteFriendById(@PathVariable Long friendId)
    {
        log.info("根据好友ID删除好友:{}",friendId);
        userService.deleteFriendById(friendId);
        return Result.success();
    }
}
