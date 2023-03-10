package com.dotto.app.search.controller;

import com.dotto.app.common.response.Response;
import com.dotto.app.search.service.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(value = "Search Controller", tags = "Search")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @ApiOperation(value = "키워드 검색", notes = "키워드 검색을 한다")
    @GetMapping("/search/{searchKeyword}")
    @ResponseStatus(HttpStatus.OK)
    public Response keywordSearch(
            @ApiParam(value = "검색 키워드", required = true)
            @PathVariable("searchKeyword") String searchKeyword){
        return Response.success(searchService.search(searchKeyword));
    }
}
