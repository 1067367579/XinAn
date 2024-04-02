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
@ApiModel(value = "信息实体")
public class Message {
    @ApiModelProperty(value = "信息记录id")
    private Long id;
    @ApiModelProperty(value = "发送者用户id")
    private Long senderId;
    @ApiModelProperty(value = "接收者用户id")
    private Long receiverId;
    @ApiModelProperty(value = "包内容id")
    private Long packageId;
    @ApiModelProperty(value = "包内容种类 0好友请求 1密码发送 2宠物发送")
    private Integer packageCategory;
}
