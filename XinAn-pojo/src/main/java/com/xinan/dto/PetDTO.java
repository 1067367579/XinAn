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
@ApiModel(value = "宠物信息数据传输模型")
public class PetDTO {
    @ApiModelProperty(value = "宠物名")
    private String name;
    @ApiModelProperty(value = "宠物图片路径")
    private String image;
}
