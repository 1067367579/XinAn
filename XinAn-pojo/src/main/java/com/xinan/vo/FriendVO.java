package com.xinan.vo;

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
@ApiModel(value = "好友列表信息数据模型")
public class FriendVO {
    @ApiModelProperty("好友的用户id")
    private Long id;
    @ApiModelProperty("好友的备注名 默认就是用户名")
    private String remarkName;
    @ApiModelProperty("好友的头像图片路径")
    private String avatar;
    @ApiModelProperty("好友手机号")
    private String phone;
    @ApiModelProperty("好友的分类id")
    private Long friendCategoryId;
}
