package com.xinan.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "安心圈点赞数据传输模型")
public class MomentLikesDTO {
    @ApiModelProperty(value = "点赞的用户id")
    private Long userId;
    @ApiModelProperty(value = "安心圈id")
    private Long momentId;
}
