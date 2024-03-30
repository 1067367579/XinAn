package com.xinan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "返回给前端的分类查询用户数据模型")
public class InvitationVO {
    @ApiModelProperty(value = "好友分类id")
    private Long friendCategoryId;
    @ApiModelProperty(value = "好友分类名称")
    private String categoryName;
    @ApiModelProperty(value = "邀请名单好友信息集合")
    private List<FriendInvitationVO> friends;
}
