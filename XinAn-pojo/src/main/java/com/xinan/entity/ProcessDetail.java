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
@ApiModel(value = "流程细则实体")
public class ProcessDetail {
    @ApiModelProperty(value = "流程细则id")
    private Long id;
    @ApiModelProperty(value = "流程id")
    private Long processId;
    @ApiModelProperty(value = "流程细则标题")
    private String title;
    @ApiModelProperty(value = "流程细则内容")
    private String content;
    @ApiModelProperty(value = "该条记录在单个流程下的次序")
    private Integer order;
}
