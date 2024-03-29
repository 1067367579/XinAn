package com.xinan.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "安心圈动态实体")
public class Moment implements Serializable {
    @ApiModelProperty(value = "安心圈动态id")
    private Long id;
    @ApiModelProperty(value = "发布动态用户id")
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
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
}
