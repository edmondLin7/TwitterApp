package com.dbrowne.twitter.tweet.service.repository;


import com.dbrowne.twitter.tweet.service.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {
    List<Tweet> findAllByUserId(Long userId);

}

