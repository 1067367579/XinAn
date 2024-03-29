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
@ApiModel(value = "返回给前端的点赞数据模型")
public class MomentLikesVO {
    @ApiModelProperty(value = "点赞id")
    private Long id;
    @ApiModelProperty(value = "用户名(好友则显示备注名)")
    private String username;
}
