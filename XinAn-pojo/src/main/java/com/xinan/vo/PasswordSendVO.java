package com.xinan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "密码发送信息数据模型")
public class PasswordSendVO {
    @ApiModelProperty(value = "密码id")
    //信息对象直接获取
    private Long packageId;
    @ApiModelProperty(value = "发送者备注名")
    //根据自己的id和发送者id到好友表中查找
    private String remarkName;
    @ApiModelProperty(value = "发送者头像图片路径")
    //根据发送者id到用户表中查找
    private String image;
    @ApiModelProperty(value = "平台名称")
    //根据包id到密码表中查找
    private String platformName;
    @ApiModelProperty(value = "账号")
    //根据包id到密码表中查找
    private String account;
    @ApiModelProperty(value = "密码")
    //根据包id到密码表中查找
    private String password;
}
