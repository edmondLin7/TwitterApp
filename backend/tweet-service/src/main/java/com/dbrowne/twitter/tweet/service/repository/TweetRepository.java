package com.cogent.twitter.backend.repository;

import com.cogent.twitter.backend.entity.Tweet;
import com.cogent.twitter.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {
    List<Tweet> findAllByUserLoginId(String username);}

