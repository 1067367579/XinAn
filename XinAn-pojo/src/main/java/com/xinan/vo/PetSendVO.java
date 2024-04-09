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
    //直接从查到的信息对象中获取
    @ApiModelProperty(value = "宠物id")
    private Long petId;
    //在好友表中查询得到
    @ApiModelProperty(value = "发送者备注名")
    private String remarkName;
    //在用户表中查询得到
    @ApiModelProperty(value = "发送者头像图片路径")
    private String image;
    //在宠物表中查询得到
    @ApiModelProperty(value = "宠物名")
    private String petName;
    //在宠物表中查询得到
    @ApiModelProperty(value = "宠物图片路径")
    private String petImage;
}
