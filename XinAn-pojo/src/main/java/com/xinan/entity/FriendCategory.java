package com.xinan.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ApiModel("好友分类实体模型")
public class FriendCategory {
    @ApiModelProperty(value = "好友分类id")
    private Long id;
    @ApiModelProperty(value = "用户Id 该分类属于哪一个用户")
    private Long userId;
    @ApiModelProperty(value = "分类名称")
    private String name;
}
