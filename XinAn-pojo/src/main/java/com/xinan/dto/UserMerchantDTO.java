package com.xinan.dto;

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
@ApiModel(value = "用户收藏商家数据传输模型")
public class UserMerchantDTO {
    @ApiModelProperty(value = "待收藏的商家id")
    private Long merchantId;
}
