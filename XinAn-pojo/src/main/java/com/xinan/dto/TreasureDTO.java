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
@ApiModel(value = "珍宝数据传输模型")
public class TreasureDTO {
    @ApiModelProperty(value = "珍宝名称")
    private String name;
    @ApiModelProperty(value = "珍宝图片路径")
    private String image;
}
