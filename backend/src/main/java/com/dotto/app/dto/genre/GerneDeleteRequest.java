package com.dotto.app.dto.genre;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "장르 삭제")
@Data
@AllArgsConstructor
public class GerneDeleteRequest {

    @ApiModelProperty(value = "삭제할 장르명", notes = "삭제할 장르명을 입력한다.")
    @NotBlank
    private String deleteGenreName;
}
