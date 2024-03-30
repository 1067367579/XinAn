package com.xinan.vo;

import com.xinan.entity.ProcessDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel(value = "返回给前端的流程数据模型")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProcessVO {
    @ApiModelProperty(value = "流程id")
    private Long id;
    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value = "流程名")
    private String name;
    @ApiModelProperty(value = "流程简要描述")
    private String illustrate;
    @ApiModelProperty(value = "流程状态 0未完成 1已完成")
    private Integer status;
    @ApiModelProperty(value = "流程排序")
    private Integer order;
    @ApiModelProperty(value = "流程细则集合")
    private List<ProcessDetail> detail;
}
