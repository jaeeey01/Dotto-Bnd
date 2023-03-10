package com.dotto.app.follow.controller;

import com.dotto.app.common.response.Response;
import com.dotto.app.follow.service.FollowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(value = "Follow Controller", tags = "Follow")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @ApiOperation(value = "팔로우 요청", notes = "팔로우를 한다")
    @GetMapping("/follow")
    @ResponseStatus(HttpStatus.CREATED)
    public Response follow(
            @ApiParam(value = "follow 하는 사람")
            Long followingNo,
            @ApiParam(value = "follow 당한 사람")
            Long followerNo) {
        return Response.success(followService.follow(followingNo, followerNo));

    }

    @ApiOperation(value = "언팔로우 요청", notes = "언팔로우를 한다")
    @DeleteMapping("/unfollow")
    @ResponseStatus(HttpStatus.OK)
    public Response unfollow(
            @ApiParam(value = "unfollow 하는 사람")
            Long followingNo,
            @ApiParam(value = "unfollow 당한 사람")
            Long followerNo){
        return Response.success(followService.unfollow(followingNo, followerNo));
    }

    @ApiOperation(value = "팔로우 상태 확인", notes = "팔로우/팔로잉 상태 확인 한다")
    @GetMapping("/followcheck")
    @ResponseStatus(HttpStatus.OK)
    public Response followCheck(
            @ApiParam(value = "follow 하는 사람")
            Long followingNo,
            @ApiParam(value = "follow 당한 사람")
            Long followerNo){
        return Response.success(followService.followCheck(followingNo, followerNo));
    }

    @ApiOperation(value = "팔로잉 수 확인", notes = "팔로잉 수 확인을 한다")
    @GetMapping("/following/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response following(
            @ApiParam(value = "memberNo")
            @PathVariable(value = "id") Long memberNo){
        return Response.success(followService.followingCheck(memberNo));
    }

    @ApiOperation(value = "팔로우 수 확인", notes = "팔로우 수 확인을 한다")
    @GetMapping("/follower/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response follower(
            @ApiParam(value = "memberNo")
            @PathVariable(value = "id")Long memberNo){
        return Response.success(followService.followerCheck(memberNo));
    }

    @ApiOperation(value = "팔로워 하는 사람 프로필 조회", notes = "팔로워하는 사람의 프로필 정보를 조회 한다.(프로필 이미지 미구현)")
    @GetMapping("/followlists/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response FollowerLists(@PathVariable(value = "id") Long memberNo){

        return Response.success(followService.followerLists(memberNo));
    }

    @ApiOperation(value = "팔로잉 하는 사람 프로필 조회", notes = "팔로잉하는 사람의 프로필 정보를 조회 한다.(프로필 이미지 미구현)")
    @GetMapping("/followinglists/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response FollowingLists(@PathVariable(value = "id") Long memberNo){

        return Response.success(followService.followingLists(memberNo));
    }

}
