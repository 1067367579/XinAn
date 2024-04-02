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
@ApiModel(value = "宠物发送信息数据模型")
public class PetSendVO {
    @ApiModelProperty(value = "发送表条目id")
    private Long id;
    @ApiModelProperty(value = "发送者备注名")
    private String remarkName;
    @ApiModelProperty(value = "发送者头像图片路径")
    private String image;
    @ApiModelProperty(value = "宠物名")
    private String petName;
    @ApiModelProperty(value = "宠物图片路径")
    private String petImage;
}
