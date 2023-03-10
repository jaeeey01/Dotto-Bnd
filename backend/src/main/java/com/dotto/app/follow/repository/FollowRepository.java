package com.dotto.app.follow.repository;

import com.dotto.app.follow.dto.FollowerList;
import com.dotto.app.follow.dto.FollowingList;
import com.dotto.app.follow.entity.Follow;
import com.dotto.app.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow,Long> {


    int countFollowByFollowingAndFollower(Member following, Member follower); // 팔로우 되어있는지 count하는 메서드

    void deleteByFollowingAndFollower(Member following, Member follower); //언팔로우 메서드

    @Query("select count (f) from Follow f join Member m on m = f.following where m.memberNo= :memberNo ")
    int followingCheck(Long memberNo);

    @Query("select count(f) from Follow f join Member m on m = f.follower where m.memberNo= :memberNo ")
    int followerCheck(Long memberNo);

    @Query("select new com.dotto.app.follow.dto.FollowerList(m.memberNo, m.nickname) from Member m where m.memberNo in(select f.follower.memberNo from Follow f where f.following.memberNo = :memberNo)")
    List<FollowerList> findByFollowerList(Long memberNo);

    @Query("select new com.dotto.app.follow.dto.FollowingList(m.memberNo, m.nickname) from Member m where m.memberNo in(select f.following.memberNo from Follow f where f.follower.memberNo = :memberNo)")
    List<FollowingList> findByFollowingList(Long memberNo);


}
