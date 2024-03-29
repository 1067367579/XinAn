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
@ApiModel(value = "返回给前端的商家信息对象")
public class MerchantVO {
    @ApiModelProperty(value = "商家id")
    private Long id;
    @ApiModelProperty(value = "商家名")
    private String name;
    @ApiModelProperty(value = "商家分类")
    private String merchantCategory;
    @ApiModelProperty(value = "商家地址")
    private String merchantAddress;
    @ApiModelProperty(value = "负责人")
    private String leader;
    @ApiModelProperty(value = "手机号")
    private String phone;
}
