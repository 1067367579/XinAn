package com.xinan.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ApiModel(value = "商家实体")
public class Merchant {
    @ApiModelProperty(value = "商家id")
    private Long id;
    @ApiModelProperty(value = "商家名")
    private String name;
    @ApiModelProperty(value = "商家分类id")
    private Long merchantCategoryId;
    @ApiModelProperty(value = "负责人")
    private String leader;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "城市名")
    private String city;
    @ApiModelProperty(value = "区/县名")
    private String district;
    @ApiModelProperty(value = "详细地址")
    private String detail;
    @ApiModelProperty(value = "商家所在位置的纬度")
    private Double lat;
    @ApiModelProperty(value = "商家所在位置的经度")
    private Double lng;
}
