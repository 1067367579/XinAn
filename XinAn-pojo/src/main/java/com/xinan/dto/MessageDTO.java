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
@ApiModel(value = "信息记录数据传输模型")
public class MessageDTO {
    @ApiModelProperty(value = "接收者用户id")
    private Long receiverId;
    @ApiModelProperty(value = "包内容id")
    private Long packageId;
}
