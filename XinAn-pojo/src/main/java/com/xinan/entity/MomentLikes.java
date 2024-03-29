package com.xinan.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "用户点赞安心圈实体")
public class MomentLikes {
    @ApiModelProperty(value = "点赞id")
    private Long id;
    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value = "安心圈id")
    private Long momentId;
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
}
