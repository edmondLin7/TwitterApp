package com.cogent.twitter.backend.service;

import com.cogent.twitter.backend.entity.Tweet;
import com.cogent.twitter.backend.entity.User;
import com.cogent.twitter.backend.repository.ReplyRepository;
import com.cogent.twitter.backend.repository.TweetRepository;
import com.cogent.twitter.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.cogent.twitter.backend.entity.Reply;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReplyService {
    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Reply> getAllRepliesByTweet(String username, Long tweetId) {
        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new RuntimeException("Tweet not found"));
        return replyRepository.findAllByTweet(tweet);
       //User user = userRepository.findUserByLoginId(username)
        //       .orElseThrow(() -> new RuntimeException("User not found"));
    }



    // Method to retrieve all replies for a given tweet ID
    // Method to post a reply to a tweet
    public Reply replyToTweet(String username, Long tweetId, Reply reply) {
        // Retrieve the tweet using tweetId from the tweet repository
        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new RuntimeException("Tweet not found"));

        // Retrieve the user using username from the user repository
        User user = userRepository.findUserByLoginId(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Associate the reply with the tweet
        reply.setTweet(tweet);

        // Associate the reply with the user
        reply.setUser(user);
        reply.setTimestamp(LocalDateTime.now());
        reply.setLikeCount(0L);
        // Save the reply in the database using the ReplyRepository
        return replyRepository.save(reply);
    }
}

