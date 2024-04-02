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
@ApiModel(value = "封装改动之后的流程id和排序的数据传输模型")
public class ProcessOrderDTO {
    @ApiModelProperty(value = "流程id")
    private Long id;
    @ApiModelProperty(value = "排序数 越大排序越后")
    private Integer order;
}
