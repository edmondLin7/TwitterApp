package com.cogent.twitter.backend.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
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
}
