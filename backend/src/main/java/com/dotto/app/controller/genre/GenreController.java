package com.dotto.app.controller.genre;

import com.dotto.app.dto.genre.GenreCreateRequest;
import com.dotto.app.dto.genre.GenreUpdateRequest;
import com.dotto.app.dto.genre.GenreDeleteRequest;
import com.dotto.app.dto.response.Response;
import com.dotto.app.service.genre.GenreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(value = "Genre Controller", tags = "Genre")
@RestController
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @ApiOperation(value = "장르 생성", notes = "장르를 생성한다")
    @PostMapping("/api/genre")
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@RequestBody GenreCreateRequest req){

        return Response.success(genreService.create(req));
    }

    @ApiOperation(value = "장르명 변경", notes = "장르명을 변경한다")
    @PutMapping("/api/genre")
    @ResponseStatus(HttpStatus.OK)
    public Response update(@RequestBody GenreUpdateRequest req){
        return Response.success(genreService.update(req));
    }

    @ApiOperation(value = "장르 삭제", notes = "장르를 삭제한다")
    @DeleteMapping("/api/genre")
    @ResponseStatus(HttpStatus.OK)
    public Response delete(@RequestBody GenreDeleteRequest req){
        return Response.success(genreService.delete(req));
    }
}
