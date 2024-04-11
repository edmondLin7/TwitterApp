package com.cogent.twitter.backend.repository;

import com.cogent.twitter.backend.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
}

