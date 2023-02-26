package com.dotto.app.feed.repository;

import com.dotto.app.feed.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FeedRepository extends JpaRepository<Feed, Long> {

    @Query("select f from Feed f where f.deletedYn='N' and f.feedNo=:feedNo" )
    Optional<Feed> findByFeedNoWithDeletedYnEqualsN(Long feedNo);
}
