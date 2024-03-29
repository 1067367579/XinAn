package com.xinan.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "安心圈动态数据传输对象")
public class MomentDTO implements Serializable {
    @ApiModelProperty(value = "安心圈动态id(修改时需要)")
    private Long id;
    @ApiModelProperty(value = "发布用户id")
    private Long userId;
    @ApiModelProperty(value = "安心圈动态标题")
    private String title;
    @ApiModelProperty(value = "安心圈动态副标题")
    private String subTitle;
    @ApiModelProperty(value = "图片1路径")
    private String photo1;
    @ApiModelProperty(value = "图片2路径")
    private String photo2;
    @ApiModelProperty(value = "图片3路径")
    private String photo3;
    @ApiModelProperty(value = "图片4路径")
    private String photo4;
    @ApiModelProperty(value = "开始日期")
    private LocalDate beginDate;
    @ApiModelProperty(value = "结束日期")
    private LocalDate endDate;
}
