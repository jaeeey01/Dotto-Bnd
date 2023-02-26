package com.dotto.app.genre.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@ApiModel(value = "장르 목록 조회")
@Data
@AllArgsConstructor
public class GenreListResponse {

    private List<String> genreList;
}
