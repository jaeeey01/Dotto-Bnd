package com.dotto.app.genre.controller;

import com.dotto.app.genre.dto.GenreCreateRequest;
import com.dotto.app.genre.dto.GenreUpdateRequest;
import com.dotto.app.genre.dto.GenreDeleteRequest;
import com.dotto.app.common.response.Response;
import com.dotto.app.genre.service.GenreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(value = "Genre Controller", tags = "Genre")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @ApiOperation(value = "장르 생성", notes = "장르를 생성한다")
    @PostMapping("/genre")
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@RequestBody GenreCreateRequest req){

        return Response.success(genreService.create(req));
    }

    @ApiOperation(value = "장르명 변경", notes = "장르명을 변경한다")
    @PutMapping("/genre")
    @ResponseStatus(HttpStatus.OK)
    public Response update(@RequestBody GenreUpdateRequest req){
        return Response.success(genreService.update(req));
    }

    @ApiOperation(value = "장르 삭제", notes = "장르를 삭제한다")
    @DeleteMapping("/genre")
    @ResponseStatus(HttpStatus.OK)
    public Response delete(@RequestBody GenreDeleteRequest req){
        return Response.success(genreService.delete(req));
    }

    @ApiOperation(value = "장르 목록 조회", notes = "장르 목록을 조회한다")
    @GetMapping("/genre")
    @ResponseStatus(HttpStatus.OK)
    public Response read(){
        return Response.success(genreService.read());
    }
}
