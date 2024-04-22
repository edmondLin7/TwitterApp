package com.dbrowne.replyservice.service;

import com.dbrowne.replyservice.model.ReplyData;
import com.dbrowne.replyservice.model.Tweet;
import com.dbrowne.replyservice.model.User;
import com.dbrowne.replyservice.external.AuthService;
import com.dbrowne.replyservice.external.TweetService;
import com.dbrowne.replyservice.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.dbrowne.replyservice.entity.Reply;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReplyService {
    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private TweetService tweetService;

    @Autowired
    private AuthService authService;

    // need to handle errors
    public List<Reply> getAllRepliesByTweet(String username, Long tweetId) {
        // Retrieve all replies associated with the given tweetId
        return replyRepository.findAllByTweetID(tweetId);
    }

    public ReplyData replyToTweet(String username, Long tweetId, Reply reply) {
        // Retrieve the tweet using tweetId from the tweet repository
        Tweet tweet = tweetService.getTweetById(tweetId).getBody();
        if (tweet == null) {
            // Handle the case where the tweet is not found
            throw new RuntimeException("Tweet not found");
        }

        // Retrieve the user using username from the user repository
        User user = authService.getUserByUsername(username).getBody();
        if (user == null) {
            // Handle the case where the user is not found
            throw new RuntimeException("User not found");
        }

        // Create ReplyData object for frontend response
        ReplyData replyData = new ReplyData();
        replyData.setTweet(tweet);
        replyData.setUser(user);
        replyData.setTimestamp(LocalDateTime.now());
        replyData.setLikeCount(0L);

        // Create Reply object for database storage
        Reply replyForDb = new Reply();
        replyForDb.setTweetID(tweetId);
        replyForDb.setUserID(user.getUserId());
        replyForDb.setReplyContent(reply.getReplyContent());
        replyForDb.setTag(reply.getTag());
        replyForDb.setTimestamp(LocalDateTime.now());
        replyForDb.setLikeCount(0L);

        // Save Reply object to the database
        replyRepository.save(replyForDb);

        return replyData;
    }


    public Reply likeReply(String username, Long replyId) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new RuntimeException("This shouldn't happen from the frontend"));
        reply.setLikeCount(reply.getLikeCount() + 1);
        return replyRepository.save(reply);
    }


}

