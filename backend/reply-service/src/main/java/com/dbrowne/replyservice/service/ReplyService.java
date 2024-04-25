package com.dbrowne.replyservice.service;

import com.dbrowne.replyservice.model.ReplyData;
import com.dbrowne.replyservice.model.Tweet;
import com.dbrowne.replyservice.model.User;
import com.dbrowne.replyservice.external.AuthService;
import com.dbrowne.replyservice.external.TweetService;
import com.dbrowne.replyservice.repository.ReplyRepository;
import feign.FeignException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import com.dbrowne.replyservice.entity.Reply;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Log4j2
@Service
public class ReplyService {
    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private TweetService tweetService;

    @Autowired
    private AuthService authService;

    public ReplyService(ReplyRepository replyRepository, TweetService tweetService, AuthService authService) {
        this.replyRepository = replyRepository;
        this.tweetService = tweetService;
        this.authService = authService;
    }
    // need to handle errors
    public List<ReplyData> getAllRepliesByTweet(String username, Long tweetId) {
        // Retrieve all replies associated with the given tweetId
        List<Reply> replyList = replyRepository.findAllByTweetId(tweetId);
        return replyList.stream()
                .filter(Objects::nonNull)  // Filter out any null replies
                .map(this::buildReplyDataFromReply)
                .filter(Objects::nonNull)
                .toList();
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
        replyForDb.setTweetId(tweetId);
        replyForDb.setUserId(user.getUserId());
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
    public ReplyData buildReplyDataFromReply(Reply reply) {
        ResponseEntity<User> user;
        ResponseEntity<Tweet> tweet;
        try {
            user = authService.getUserById(reply.getUserId());
        } catch (FeignException.FeignClientException ex) {
            log.error("User not found -- continuing");
            return null;
        } catch (Exception ex) {
            log.error("Unknown exception");
            log.error(ex);
            return null;
        }
        try {
            tweet = tweetService.getTweetById(reply.getTweetId());
        } catch (FeignException.FeignClientException ex) {
            log.error("Tweet not found -- continuing");
            return null;
        }

        return ReplyData.builder()
                .replyId(reply.getReplyId())
                .replyContent(reply.getReplyContent())
                .tag(reply.getTag())
                .timestamp(reply.getTimestamp())
                .likeCount(reply.getLikeCount())
                .tweet(tweet.getBody())
                .user(user.getBody())
                .build();
    }
}

