package com.cogent.twitter.backend.service;

import com.cogent.twitter.backend.entity.Tweet;
import com.cogent.twitter.backend.entity.User;
import com.cogent.twitter.backend.repository.TweetRepository;
import com.cogent.twitter.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TweetService {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private UserRepository userRepository;

    // How would this need to change at scale--finding all tweets would be too expensive to happen in app, replace with findNTweets?
    public List<Tweet> getAllTweets() {
        return tweetRepository.findAll();
    }

    public List<Tweet> getAllTweetsByUser(String username) {
        return tweetRepository.findAllByUserLoginId(username);
    }

    public Tweet postTweet(String username, Tweet tweet) {
        User user = userRepository.findUserByLoginId(username)
                // Should implement a custom exception
                .orElseThrow(() -> new RuntimeException("User not found"));
        tweet.setUser(user);
        tweet.setTimestamp(LocalDateTime.now());
        return tweetRepository.save(tweet);
    }

    public Tweet updateTweet(String username, Long id, Tweet newTweet) {
        User user = userRepository.findUserByLoginId(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Tweet oldTweet = tweetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tweet not found"));
        if (user != oldTweet.getUser()) {
            throw new RuntimeException("Cannot update a tweet that is not owned by you");
        }
        oldTweet.setTweetContent(newTweet.getTweetContent());
        oldTweet.setTag(newTweet.getTag());
        oldTweet.setTimestamp(LocalDateTime.now());
        return tweetRepository.save(oldTweet);
    }

    public String deleteTweet(String username, Long id) {
        User user = userRepository.findUserByLoginId(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        // Maybe we just do nothing in this case -- but it really shouldn't happen how do you delete a tweet that doesnt exist
        Tweet tweet = tweetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tweet not found"));
        if (user != tweet.getUser()) {
            throw new RuntimeException("Cannot delete a tweet that is not owned by you");
        }
        tweetRepository.delete(tweet);
        return "Tweet Deleted Successfully";
    }

    public Tweet likeTweet(String username, Long id) {
        User user = userRepository.findUserByLoginId(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        // Maybe we just do nothing in this case -- but it really shouldn't happen how do you delete a tweet that doesnt exist
        Tweet tweet = tweetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tweet not found"));
        tweet.setLikeCount(tweet.getLikeCount() + 1);
        return tweetRepository.save(tweet);
    }

}
