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
@ApiModel(value = "平台管理数据传输模型")
public class PlatformDTO {
    @ApiModelProperty(value = "平台名称")
    private String name;
    @ApiModelProperty(value = "平台图片路径")
    private String image;
}
