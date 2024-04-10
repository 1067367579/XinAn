package com.xinan.dto;

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
@ApiModel(value = "服务流程数据传输模型")
public class ProcessDTO {
    @ApiModelProperty(value = "服务简单描述")
    private String illustrate;
    @ApiModelProperty(value = "服务流程名")
    private String name;
}
