package com.xinan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "返回给前端的用户数据模型")
public class UserVO {
    @ApiModelProperty(value = "用户id")
    private Long id;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "用户个性签名")
    private String signature;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "性别")
    private int gender;
    @ApiModelProperty(value = "生日")
    private LocalDate birthday;
    @ApiModelProperty(value = "用户头像图片路径")
    private String avatar;
    @ApiModelProperty(value = "用户页背景图片路径")
    private String backgroundImage;
    @ApiModelProperty(value = "愿望数")
    private Integer wishCount;
    @ApiModelProperty(value = "清单进度")
    private Integer listProcess;
    @ApiModelProperty(value = "好友数")
    private Integer friendCount;
}
