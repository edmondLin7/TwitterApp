package com.dbrowne.twitter.auth.service.repository;


import com.dbrowne.twitter.auth.service.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {
    List<Tweet> findAllByUserLoginId(String username);}

