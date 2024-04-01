package com.xinan.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "封装改动之后的流程id和排序的数据模型")
public class ProcessOrderDTO {
    private Long id;
    private Integer order;
}
