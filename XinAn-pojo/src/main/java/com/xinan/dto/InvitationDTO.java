package com.xinan.dto;

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
@ApiModel(value = "葬礼邀请操作数据传输模型")
public class InvitationDTO {
    @ApiModelProperty(value = "好友id")
    private Long friendId;
    @ApiModelProperty(value = "邀请状态 0未邀请 1已邀请")
    private Integer invited;
}
