package com.xinan.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "清单页数据传输模型")
public class CheckListDTO {
    private Long id;
    private Integer inviteState;
    private Integer wishState;
    private Integer socialPlatformState;
    private Integer passwordBoxState;
    private Integer petState;
    private Integer treasureState;
}
