package com.dotto.app.dto.genre;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "장르 수정 요청")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreUpdateRequest {

    @ApiModelProperty(value = "기존 장르명", notes = "수정 대상의 장르명을 입력해 줍니다.", required = true, example = "타투")
    @NotBlank
    private String GenreName;

    @ApiModelProperty(value = "수정하려는 장르명", notes = "수정하려는 장르명을 입력해 줍니다.", required = true, example = "닷투")
    @NotBlank
    private String updateGenreName;
}
