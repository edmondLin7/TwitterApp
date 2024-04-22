package com.dbrowne.replyservice.model;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tweet {

    private Long tweetID;

    private Long userID;

    private String tweetContent;

    private String tag;

    private LocalDateTime timestamp;
    private Long likeCount;

    public static class ReplyData {
        private Long replyID;
        private Tweet tweet;

        private User user;

        private String replyContent;

        private String tag;
        private LocalDateTime timestamp;
        private Long likeCount;
    }
}
