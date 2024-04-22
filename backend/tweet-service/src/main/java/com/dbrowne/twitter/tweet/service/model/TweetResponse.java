package com.dbrowne.twitter.tweet.service.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TweetResponse {
    private Long tweetId;

    private User user;

    private String tweetContent;

    private String tag;

    private LocalDateTime timestamp;
    private Long likeCount;
}
