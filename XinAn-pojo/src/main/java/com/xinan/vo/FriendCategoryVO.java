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
@ApiModel(value = "返回给前端的好友分类数据模型")
public class FriendCategoryVO {
    @ApiModelProperty(value = "好友分类id")
    Long id;
    @ApiModelProperty(value = "分类名称")
    String name;
}
