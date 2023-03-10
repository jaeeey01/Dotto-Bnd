package com.dotto.app.follow.service;

import com.dotto.app.follow.dto.FollowerList;
import com.dotto.app.follow.dto.FollowingList;
import com.dotto.app.follow.entity.Follow;
import com.dotto.app.member.entity.Member;
import com.dotto.app.exception.MemberNotFoundException;
import com.dotto.app.follow.repository.FollowRepository;
import com.dotto.app.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class FollowService {

    private final FollowRepository followRepository;

    private final MemberRepository memberRepository;

    @Transactional
    public boolean follow(Long followingNo, Long followerNo){
        Member following = memberRepository.findByMemberNoAndDeletedYnEqualsN(followingNo).orElseThrow(MemberNotFoundException::new);
        Member follower = memberRepository.findByMemberNoAndDeletedYnEqualsN(followerNo).orElseThrow(MemberNotFoundException::new);
        int i = followRepository.countFollowByFollowingAndFollower(following,follower);
        if(i==0){   //사전 팔로우/팔로잉 중복 체크
            followRepository.save(new Follow(following, follower));
            return true;
        }else {
            return false;
        }

    }

    @Transactional
    public boolean unfollow(Long followingNo, Long followerNo){
        Member following = memberRepository.findByMemberNoAndDeletedYnEqualsN(followingNo).orElseThrow(MemberNotFoundException::new);
        Member follower = memberRepository.findByMemberNoAndDeletedYnEqualsN(followerNo).orElseThrow(MemberNotFoundException::new);
        int i = followRepository.countFollowByFollowingAndFollower(following,follower);
        if(i==1){   //사전 팔로우/팔로잉 체크
            followRepository.deleteByFollowingAndFollower(following, follower);
            return true;
        }else {
            return false;
        }

    }

    public boolean followCheck(Long followingNo, Long followerNo){
        Member following = memberRepository.findByMemberNoAndDeletedYnEqualsN(followingNo).orElseThrow(MemberNotFoundException::new);
        Member follower = memberRepository.findByMemberNoAndDeletedYnEqualsN(followerNo).orElseThrow(MemberNotFoundException::new);
        int i = followRepository.countFollowByFollowingAndFollower(following, follower);
        if(i==0){
            return false;   //팔로우 안되어 있음
        }
        return true;       //팔로우 되어 있음
    }

    public int followingCheck(Long memberNo){

        return followRepository.followingCheck(memberNo);

    }

    public int followerCheck(Long memberNo){
        return followRepository.followerCheck(memberNo);
    }

    public List<FollowerList> followerLists(Long memberNo){
        return followRepository.findByFollowerList(memberNo);

    }

    public List<FollowingList> followingLists(Long memberNo){
        return followRepository.findByFollowingList(memberNo);
    }

}
