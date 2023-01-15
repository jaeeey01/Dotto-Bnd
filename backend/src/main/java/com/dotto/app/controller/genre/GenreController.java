package com.dotto.app.controller.genre;

import com.dotto.app.dto.genre.GenreCreateRequest;
import com.dotto.app.dto.response.Response;
import com.dotto.app.service.genre.GenreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Genre Controller", tags = "Genre")
@RestController
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @ApiOperation(value = "장르 생성", notes = "장르를 생성한다")
    @PostMapping("/api/genre")
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(GenreCreateRequest req){

        return Response.success(genreService.create(req));
    }

}
