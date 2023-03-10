package com.dotto.app.genre.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "장르 삭제")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreDeleteRequest {

    @ApiModelProperty(value = "삭제할 장르명", notes = "삭제할 장르명을 입력한다.", required = true, example = "타투")
    @NotBlank
    private String genreName;
}
