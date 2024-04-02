package com.xinan.entity;

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
@ApiModel(value = "平台信息实体")
public class Platform {
    @ApiModelProperty(value = "平台信息id")
    private Long id;
    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value = "平台名称")
    private String name;
    @ApiModelProperty(value = "平台图片路径")
    private String image;
    @ApiModelProperty(value = "平台状态 0未处理 1注销 2转让 3保留")
    private Integer status;

}
