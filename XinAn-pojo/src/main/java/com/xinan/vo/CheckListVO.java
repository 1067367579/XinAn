package com.xinan.vo;

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
@ApiModel(value = "返回给前端的清单页数据模型")
public class CheckListVO {
    @ApiModelProperty(value = "清单id")
    private Long id;
    @ApiModelProperty(value = "进度显示")
    private Integer listProcess;
    @ApiModelProperty(value = "葬礼邀请状态")
    private Integer inviteState;
    @ApiModelProperty(value = "愿望状态")
    private Integer wishState;
    @ApiModelProperty(value = "社交平台管理状态")
    private Integer socialPlatformState;
    @ApiModelProperty(value = "密码柜状态")
    private Integer passwordBoxState;
    @ApiModelProperty(value = "宠物管理状态")
    private Integer petState;
    @ApiModelProperty(value = "珍宝管理状态")
    private Integer treasureState;
}
