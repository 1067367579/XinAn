package com.xinan.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ApiModel(value = "用户实体")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @ApiModelProperty(value = "用户id")
    private Long id;

    @ApiModelProperty(value = "微信openid")
    private String openid;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户手机号")
    private String phone;

    @ApiModelProperty(value = "用户性别")
    private int gender;

    @ApiModelProperty(value = "用户生日日期")
    private LocalDate birthday;

    @ApiModelProperty(value = "用户微信头像路径")
    private String avatar;

    @ApiModelProperty(value = "用户个性签名")
    private String signature;

    @ApiModelProperty(value = "用户页背景图片路径")
    private String backgroundImage;

    @ApiModelProperty(value = "用户的创建时间")
    private LocalDateTime createTime;
}
