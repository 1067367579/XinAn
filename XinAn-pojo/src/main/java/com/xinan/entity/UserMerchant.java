package com.xinan.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "用户收藏商家信息实体对象")
public class UserMerchant {
    @ApiModelProperty(value = "用户收藏商家信息id")
    private Long id;
    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value = "商户id")
    private Long merchantId;
    @ApiModelProperty(value = "收藏时间")
    private LocalDateTime createTime;
}
