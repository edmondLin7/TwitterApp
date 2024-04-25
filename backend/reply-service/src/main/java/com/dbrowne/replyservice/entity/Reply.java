package com.dbrowne.replyservice.entity;

import com.dbrowne.replyservice.model.Tweet;
import com.dbrowne.replyservice.model.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;

@Entity
@Table(name = "Reply")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;

    private Long tweetId;

    private Long userId;

    @Column(nullable = false)
    private String replyContent;

    private String tag;

    private LocalDateTime timestamp;
    private Long likeCount;

}
