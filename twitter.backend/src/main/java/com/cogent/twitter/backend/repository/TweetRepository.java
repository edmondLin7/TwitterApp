package com.cogent.twitter.backend.repository;

import com.cogent.twitter.backend.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {
}

