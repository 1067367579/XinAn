package com.xinan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "返回给前端的安心圈动态数据模型")
public class MomentVO {
    @ApiModelProperty(value = "安心圈动态id")
    private Long id;
    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "用户头像路径")
    private String avatar;
    @ApiModelProperty(value = "安心圈动态标题")
    private String title;
    @ApiModelProperty(value = "安心圈副标题")
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
    @ApiModelProperty(value = "点赞视图数据模型集合")
    private List<MomentLikesVO> momentLikes;
}
