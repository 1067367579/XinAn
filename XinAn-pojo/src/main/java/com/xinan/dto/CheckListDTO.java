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
@ApiModel(value = "清单页数据传输模型")
public class CheckListDTO {
    @ApiModelProperty(value = "清单id")
    private Long id;
    @ApiModelProperty(value = "葬礼邀请状态")
    private Integer inviteState;
    @ApiModelProperty(value = "愿望清单状态")
    private Integer wishState;
    @ApiModelProperty(value = "社交平台管理状态")
    private Integer socialPlatformState;
    @ApiModelProperty(value = "密码柜管理状态")
    private Integer passwordBoxState;
    @ApiModelProperty(value = "宠物管理状态")
    private Integer petState;
    @ApiModelProperty(value = "珍宝管理状态")
    private Integer treasureState;
}
