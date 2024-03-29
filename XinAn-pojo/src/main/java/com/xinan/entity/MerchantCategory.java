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
@ApiModel(value = "商家分类实体对象")
public class MerchantCategory {
    @ApiModelProperty(value = "商家分类id")
    private Long id;
    @ApiModelProperty(value = "商家分类名称")
    private String name;
}
