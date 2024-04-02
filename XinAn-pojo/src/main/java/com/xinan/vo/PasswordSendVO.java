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
    private Long packageId;
    @ApiModelProperty(value = "发送者备注名")
    private String remarkName;
    @ApiModelProperty(value = "发送者头像图片路径")
    private String image;
    @ApiModelProperty(value = "平台名称")
    private String platformName;
    @ApiModelProperty(value = "账号")
    private String account;
    @ApiModelProperty(value = "密码")
    private String password;
}
