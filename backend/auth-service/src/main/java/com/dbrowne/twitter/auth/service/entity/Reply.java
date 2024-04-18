package com.dbrowne.twitter.auth.service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Reply")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyID;

    @ManyToOne
    @JoinColumn(name = "tweetID")
    private Tweet tweet;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    @Column(nullable = false)
    private String replyContent;

    private String tag;

    private LocalDateTime timestamp;
    private Long likeCount;
}
