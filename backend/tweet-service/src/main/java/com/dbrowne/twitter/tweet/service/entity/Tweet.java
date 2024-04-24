package com.dbrowne.twitter.tweet.service.entity;

import com.dbrowne.twitter.tweet.service.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Entity
@Table(name = "tweet")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tweetId;

    @Column(name="user_id")
    private Long userId;

    @Column(nullable = false, name="tweet_content")
    private String tweetContent;

    private String tag;

    private LocalDateTime timestamp;
    @Column(name="like_count")
    private Long likeCount;
}
