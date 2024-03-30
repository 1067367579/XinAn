package com.xinan.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "葬礼邀请名单需要的好友信息数据模型")
public class FriendInvitationVO {
    @ApiModelProperty(value = "好友id")
    private Long friendId;
    @ApiModelProperty(value = "对好友的备注名")
    private String remarkName;
    @ApiModelProperty(value = "邀请状态 0未邀请 1已邀请")
    private Integer invited;
}
