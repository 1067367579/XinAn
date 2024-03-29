package com.xinan.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ApiModel(value = "商家地址实体对象")
public class MerchantAddress {
    @ApiModelProperty(value = "商家地址记录id")
    private Long id;
    @ApiModelProperty(value = "城市名")
    private String city;
    @ApiModelProperty(value = "县/区名")
    private String district;
    @ApiModelProperty(value = "详细地址")
    private String detail;
}
