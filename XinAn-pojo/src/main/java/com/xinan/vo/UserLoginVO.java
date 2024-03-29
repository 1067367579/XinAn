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
@ApiModel(value = "用户登录返回前端的数据模型")
public class UserLoginVO {

    @ApiModelProperty(value = "用户id")
    private Long id;
    @ApiModelProperty(value = "用户的微信openid")
    private String openid;
    @ApiModelProperty(value = "jwt令牌")
    private String token;
}
