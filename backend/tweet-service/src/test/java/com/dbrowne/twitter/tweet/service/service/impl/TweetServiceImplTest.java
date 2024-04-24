package com.dbrowne.twitter.tweet.service.service.impl;

import com.dbrowne.twitter.tweet.service.entity.Tweet;
import com.dbrowne.twitter.tweet.service.external.client.UserService;
import com.dbrowne.twitter.tweet.service.model.TweetResponse;
import com.dbrowne.twitter.tweet.service.model.User;
import com.dbrowne.twitter.tweet.service.repository.TweetRepository;
import com.dbrowne.twitter.tweet.service.service.TweetService;
import jakarta.persistence.Id;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class TweetServiceImplTest {

    @Mock
    private static TweetRepository tweetRepository;

    @Mock
    private static UserService userService;

    @InjectMocks
    private TweetServiceImpl tweetService;

    private static List<Tweet> tweets;
    private static User user1;
    private static User user2;
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
                .tag("#tagContent")
                .userId(2L)
                .build());
        user1 = new User(1L, "first", "last", "email", "username", "password", "1234567899");
        user2 = new User(2L, "first2", "last2", "email", "secondUser", "password", "1234567899");
    }

    @Test
    void getAllTweets() {
        // given
        given(tweetRepository.findAll()).willReturn(tweets);
        given(userService.getUserById(1L)).willReturn(new ResponseEntity<>(user1, HttpStatusCode.valueOf(200)));
        given(userService.getUserById(2L)).willReturn(new ResponseEntity<>(user2, HttpStatusCode.valueOf(200)));
        // when
        List<TweetResponse> tweetsOut = tweetService.getAllTweets();
        System.out.println(tweetsOut);
        // then
        assertThat(tweetsOut.get(0)).isNotNull();
        assertThat(tweetsOut.get(1)).isNotNull();
        assertThat(tweetsOut.size()).isEqualTo(3);
        assertThat(tweetsOut.get(0).getTweetId()).isEqualTo(tweets.get(0).getTweetId());
    }

    @Test
    void getAllTweetsByUsername() {
        // given
        given(tweetRepository.findAllByUserId(1L)).willReturn(tweets.subList(0, 2));
        given(userService.getUserByUsername("username")).willReturn(new ResponseEntity<>(user1, HttpStatusCode.valueOf(200)));
        given(userService.getUserById(1L)).willReturn(new ResponseEntity<>(user1, HttpStatusCode.valueOf(200)));
        // when
        List<TweetResponse> tweetsOut = tweetService.getAllTweetsByUsername("username");
        System.out.println(tweetsOut);
        // then
        assertThat(tweetsOut.get(0)).isNotNull();
        assertThat(tweetsOut.get(1)).isNotNull();
        assertThat(tweetsOut.size()).isEqualTo(2);
        assertThat(tweetsOut.get(0).getTweetId()).isEqualTo(tweets.get(0).getTweetId());
    }

    @Test
    void getAllTweetsByUserId() {
        given(tweetRepository.findAllByUserId(1L)).willReturn(tweets.subList(0, 2));
        given(userService.getUserById(1L)).willReturn(new ResponseEntity<>(user1, HttpStatusCode.valueOf(200)));
        // when
        List<TweetResponse> tweetsOut = tweetService.getAllTweetsByUserId(1L);
        System.out.println(tweetsOut);
        // then
        assertThat(tweetsOut.get(0)).isNotNull();
        assertThat(tweetsOut.get(1)).isNotNull();
        assertThat(tweetsOut.size()).isEqualTo(2);
        assertThat(tweetsOut.get(0).getTweetId()).isEqualTo(tweets.get(0).getTweetId());
    }

    @Test
    void getAllTweetsByTag() {
        List<Tweet> tagContentTweets = new ArrayList<>();
        tagContentTweets.add(tweets.get(0));
        tagContentTweets.add(tweets.get(2));
        // base repository method used
        given(tweetRepository.findAllByTag("tagContent")).willReturn(tagContentTweets);
        // needed to associate response with user
        given(userService.getUserById(1L)).willReturn(new ResponseEntity<>(user1, HttpStatusCode.valueOf(200)));
        given(userService.getUserById(2L)).willReturn(new ResponseEntity<>(user2, HttpStatusCode.valueOf(200)));

        List<TweetResponse> tweetsOutWithTag = tweetService.getAllTweetsByTag("tagContent");

        assertThat(tweetsOutWithTag).isNotNull();
        assertThat(tweetsOutWithTag.size()).isEqualTo(2);
        assertThat(tweetsOutWithTag.get(0).getTweetId()).isEqualTo(1L);
        assertThat(tweetsOutWithTag.get(1).getTweetId()).isEqualTo(3L);
    }

    @Test
    void getTweetById() {
        // given
        given(tweetRepository.findById(tweets.get(0).getTweetId())).willReturn(Optional.of(tweets.get(0)));
        given(userService.getUserById(1L)).willReturn(new ResponseEntity<>(user1, HttpStatusCode.valueOf(200)));
        TweetResponse tweetResponse = tweetService.getTweetById(tweets.get(0).getTweetId());

        assertThat(tweetResponse).isNotNull();
        assertThat(tweetResponse.getTweetId()).isEqualTo(1);
        assertThat(tweetResponse.getTweetContent()).isEqualTo("Test 1 content");
    }

    @Test
    void postTweet() {
        given(tweetRepository.save(tweets.get(0)))
                .willReturn(tweets.get(0));
        given(userService.getUserById(1L))
                .willReturn(new ResponseEntity<>(user1, HttpStatusCode.valueOf(200)));
        given(userService.getUserByUsername("username"))
                .willReturn(new ResponseEntity<>(user1, HttpStatusCode.valueOf(200)));

        TweetResponse tweetResponse = tweetService.postTweet("username", tweets.get(0));

        assertThat(tweetResponse).isNotNull();
        assertThat(tweetResponse.getTweetId()).isEqualTo(1);
        assertThat(tweetResponse.getTweetContent()).isEqualTo("Test 1 content");
    }

    @Test
    void updateTweet() {
        Tweet updateTweet = tweets.get(0);
        updateTweet.setTweetContent("New Content");
        given(tweetRepository.findById(tweets.get(0).getTweetId()))
                .willReturn(Optional.of(tweets.get(0)));
        given(tweetRepository.save(tweets.get(0)))
                .willReturn(tweets.get(0));
        given(userService.getUserById(1L))
                .willReturn(new ResponseEntity<>(user1, HttpStatusCode.valueOf(200)));
        given(userService.getUserByUsername("username"))
                .willReturn(new ResponseEntity<>(user1, HttpStatusCode.valueOf(200)));

        TweetResponse tweetResponse = tweetService.updateTweet(
                "username",
                tweets.get(0).getTweetId(),
                updateTweet);

        assertThat(tweetResponse).isNotNull();
        assertThat(tweetResponse.getTweetId()).isEqualTo(1);
        assertThat(tweetResponse.getTweetContent()).isEqualTo("New Content");
    }

    // Can delete methods really be tested with mocks -- the functionality is deleting from db and we have no real db
    @Test
    void deleteTweet() {
    }

    @Test
    void likeTweet() {
        Tweet updateTweet = tweets.get(0);
        updateTweet.setTweetContent("New Content");
        given(tweetRepository.findById(tweets.get(0).getTweetId()))
                .willReturn(Optional.of(tweets.get(0)));
        given(tweetRepository.save(tweets.get(0)))
                .willReturn(tweets.get(0));
        given(userService.getUserById(1L))
                .willReturn(new ResponseEntity<>(user1, HttpStatusCode.valueOf(200)));
        given(userService.getUserByUsername("username"))
                .willReturn(new ResponseEntity<>(user1, HttpStatusCode.valueOf(200)));

        TweetResponse tweetResponse = tweetService.likeTweet("username", 1L);

        assertThat(tweetResponse).isNotNull();
        assertThat(tweetResponse.getTweetId()).isEqualTo(1);
        assertThat(tweetResponse.getLikeCount()).isEqualTo(1L);
    }
}