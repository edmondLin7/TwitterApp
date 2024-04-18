package com.dbrowne.twitter.auth.service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Tweet")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tweetID;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    @Column(nullable = false)
    private String tweetContent;

    private String tag;

    private LocalDateTime timestamp;
    private Long likeCount;
}
