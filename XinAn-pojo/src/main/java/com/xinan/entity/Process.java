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
@ApiModel(value = "服务流程条目实体")
public class Process {
    @ApiModelProperty(value = "服务流程条目id")
    private Long id;
    @ApiModelProperty(value = "当前用户id")
    private Long userId;
    @ApiModelProperty(value = "服务流程名称")
    private String name;
    @ApiModelProperty(value = "服务流程简要描述")
    private String illustrate;
    @ApiModelProperty(value = "服务流程状态")
    private Integer status;
}
