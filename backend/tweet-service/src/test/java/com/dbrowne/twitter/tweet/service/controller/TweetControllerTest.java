package com.dbrowne.twitter.tweet.service.controller;

import com.dbrowne.twitter.tweet.service.entity.Tweet;
import com.dbrowne.twitter.tweet.service.model.TweetResponse;
import com.dbrowne.twitter.tweet.service.model.User;
import com.dbrowne.twitter.tweet.service.service.TweetService;
import com.dbrowne.twitter.tweet.service.service.impl.TweetServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class TweetControllerTest {

    @Mock
    private TweetService tweetService;

    @InjectMocks
    private TweetController tweetController;

    private List<Tweet> tweets;
    private List<TweetResponse> tweetResponses;
    private User user1;
    private User user2;

    @BeforeEach
    public void setup() {
        tweets = new ArrayList<>();
        tweets.add(Tweet.builder()
                .tweetId(1L)
                .timestamp(LocalDateTime.now())
                .likeCount(0L)
                .tweetContent("Test 1 content")
                .tag("#tagContent")
                .userId(1L)
                .build());
        tweets.add(Tweet.builder()
                .tweetId(2L)
                .timestamp(LocalDateTime.now())
                .likeCount(0L)
                .tweetContent("Test 2 content")
                .tag("#tag2Content")
                .userId(1L)
                .build());
        tweets.add(Tweet.builder()
                .tweetId(3L)
                .timestamp(LocalDateTime.now())
                .likeCount(0L)
                .tweetContent("Test 3 content")
                .tag("#tag3Content")
                .userId(2L)
                .build());
        System.out.println(tweets);
        user1 = new User(1L, "first", "last", "email", "username", "password", "1234567899");
        user2 = new User(2L, "first2", "last2", "email", "secondUser", "password", "1234567899");
        tweetResponses = buildTweetResponses(tweets);
        System.out.println(tweetResponses);
    }

    @Test
    void getAllTweets() {
        given(tweetService.getAllTweets()).willReturn(tweetResponses);

        //when
        ResponseEntity<List<TweetResponse>> output = tweetController.getAllTweets();

        assertThat(output).isNotNull();
        assertThat(output.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(output.getBody().size()).isEqualTo(3);
    }

    @Test
    void getTweetById() {
        given(tweetService.getTweetById(tweets.get(0).getTweetId()))
                .willReturn(tweetResponses.get(0));

        ResponseEntity<TweetResponse> output = tweetController.getTweetById(tweets.get(0).getTweetId());
        assertThat(output).isNotNull();
        assertThat(output.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(output.getBody().getTweetContent()).isEqualTo(tweets.get(0).getTweetContent());

    }

    @Test
    void getAllTweetsByUser() {
        given(tweetService.getAllTweetsByUsername("username")).willReturn(tweetResponses.subList(0, 2));

        ResponseEntity<List<TweetResponse>> output = tweetController.getAllTweetsByUser("username");

        assertThat(output).isNotNull();
        assertThat(output.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(output.getBody().size()).isEqualTo(2);
    }

    @Test
    void postTweet() {
        given(tweetService.postTweet("username", tweets.get(0)))
                .willReturn(tweetResponses.get(0));

        ResponseEntity<TweetResponse> output = tweetController.postTweet("username", tweets.get(0));

        assertThat(output).isNotNull();
        assertThat(output.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(output.getBody().getTweetContent()).isEqualTo(tweets.get(0).getTweetContent());
    }

    @Test
    void updateTweet() {
        Tweet updateTweet = tweets.get(0);
        updateTweet.setTweetContent("New Content");
        TweetResponse updatedResponse = tweetResponses.get(0);
        updatedResponse.setTweetContent("New Content");

        given(tweetService.updateTweet("username", 1L, updateTweet)).willReturn(updatedResponse);

        ResponseEntity<TweetResponse> output = tweetController.updateTweet("username", 1L, updateTweet);

        assertThat(output).isNotNull();
        assertThat(output.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        assertThat(output.getBody().getTweetContent()).isEqualTo(updateTweet.getTweetContent());
    }

    @Test
    void deleteTweet() {
    }

    @Test
    void likeTweet() {
        TweetResponse likedTweet = tweetResponses.get(0);
        likedTweet.setLikeCount(1L);
        given(tweetService.likeTweet("username", tweets.get(0).getTweetId())).willReturn(likedTweet);

        ResponseEntity<TweetResponse> output = tweetController.likeTweet("username", tweets.get(0).getTweetId());
        assertThat(output).isNotNull();
        assertThat(output.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(output.getBody().getLikeCount()).isEqualTo(likedTweet.getLikeCount());


    }

    private List<TweetResponse> buildTweetResponses(List<Tweet> tweets) {
        List<TweetResponse> responses = new ArrayList<>();
        for (Tweet tweet: tweets) {
            // Set user to user1 or user2
            User user = Objects.equals(tweet.getUserId(), user1.getUserId()) ? user1 : user2;
            responses.add(TweetResponse.builder()
                    .tweetContent(tweet.getTweetContent())
                    .tag(tweet.getTag())
                    .timestamp(tweet.getTimestamp())
                    .likeCount(tweet.getLikeCount())
                    .tweetId(tweet.getTweetId())
                    .user(user)
                    .build());

        }
        return responses;
    }
}