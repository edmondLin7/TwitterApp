package com.dbrowne.replyservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplyData {
    private Long replyID;

    private Tweet tweet;

    private User user;

    private String replyContent;

    private String tag;

    private LocalDateTime timestamp;
    private Long likeCount;
}
