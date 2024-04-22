package com.dbrowne.twitter.tweet.service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tweet")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
