package com.xinan.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "珍宝实体")
public class Treasure {
    @ApiModelProperty(value = "珍宝id")
    private Long id;
    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value = "珍宝名称")
    private String name;
    @ApiModelProperty(value = "珍宝图片路径")
    private String image;
}
