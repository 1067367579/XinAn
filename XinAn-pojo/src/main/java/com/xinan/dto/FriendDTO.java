package com.xinan.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@ApiModel("好友传输数据模型")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FriendDTO implements Serializable {
    @ApiModelProperty(value = "好友的用户id")
    private Long id;
    @ApiModelProperty(value = "好友分类id")
    private Long friendCategoryId;
    @ApiModelProperty(value = "好友备注名")
    private String remarkName;
    @ApiModelProperty(value = "葬礼是否邀请用户")
    private int invited;
}
