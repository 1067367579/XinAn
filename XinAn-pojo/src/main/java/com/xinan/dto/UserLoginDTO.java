package com.xinan.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@ApiModel(value = "用户登录数据传输模型")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO implements Serializable {

    @ApiModelProperty(value = "微信用户授权码")
    private String code;
/*
    @ApiModelProperty(value = "用户加密数据")
    private String encryptedData;

    @ApiModelProperty(value = "密钥偏移量")
    private String iv;
*/
}
