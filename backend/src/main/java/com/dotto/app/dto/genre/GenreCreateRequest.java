package com.dotto.app.dto.genre;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "장르 생성 요청")
@Data
@AllArgsConstructor
public class GenreCreateRequest {

    @ApiModelProperty(value = "장르명", notes = "추가하려는 장르명을 입력해 줍니다.")
    @NotBlank
    private String genreName;
}
