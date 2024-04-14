package com.cogent.twitter.backend.repository;

import com.cogent.twitter.backend.entity.Reply;
import com.cogent.twitter.backend.entity.Tweet;
import com.cogent.twitter.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findAllByUserLoginId(String loginId);
    List<Reply> findAllByTweet(Tweet tweet);
}
