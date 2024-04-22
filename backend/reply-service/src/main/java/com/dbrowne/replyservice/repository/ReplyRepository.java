package com.dbrowne.replyservice.repository;

import com.dbrowne.replyservice.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findAllByUserLoginId(String loginId);
    // List<Reply> findAllByTweet(Tweet tweet);
}
