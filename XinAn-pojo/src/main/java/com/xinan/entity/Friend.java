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
@ApiModel(value = "描述好友关系实体")
@Builder
public class Friend {
    @ApiModelProperty(value = "关系条目id")
    private Long id;
    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value = "好友id")
    private Long friendId;
    @ApiModelProperty(value = "对好友的备注名")
    private String remarkName;
    @ApiModelProperty(value = "好友关系创建时间")
    private LocalDateTime createTime;
    @ApiModelProperty(value = "好友分类id")
    private Long friendCategoryId;
    @ApiModelProperty(value = "葬礼邀请状态")
    private Integer invited;
}
