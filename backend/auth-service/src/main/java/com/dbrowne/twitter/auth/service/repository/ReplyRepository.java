package com.dbrowne.twitter.auth.service.repository;


import com.dbrowne.twitter.auth.service.entity.Reply;
import com.dbrowne.twitter.auth.service.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findAllByUserLoginId(String loginId);
    List<Reply> findAllByTweet(Tweet tweet);
}
