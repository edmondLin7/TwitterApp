package com.dbrowne.replyservice.service.impl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import com.dbrowne.replyservice.entity.Reply;
import com.dbrowne.replyservice.external.AuthService;
import com.dbrowne.replyservice.external.TweetService;
import com.dbrowne.replyservice.model.ReplyData;
import com.dbrowne.replyservice.model.Tweet;
import com.dbrowne.replyservice.model.User;
import com.dbrowne.replyservice.repository.ReplyRepository;
import com.dbrowne.replyservice.service.ReplyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class ReplyServiceImplTest {

    private ReplyRepository replyRepository;
    private TweetService tweetService;
    private AuthService authService;
    private ReplyService replyService;

    private Reply oneRe;
    private Reply twoRe;

    @BeforeEach
    public void setup() {
        replyRepository = mock(ReplyRepository.class);
        tweetService = mock(TweetService.class);
        authService = mock(AuthService.class);
        replyService = new ReplyService(replyRepository, tweetService, authService);

        List<Reply> mockReplies = new ArrayList<>();
        Reply oneRe = new Reply();
        Reply twoRe = new Reply();
        oneRe.setReplyContent("Reply one");
        oneRe.setTag("Tag one");
        oneRe.setUserId(1L);
        oneRe.setTweetId(1L);
        twoRe.setReplyContent("Reply two");
        twoRe.setTag("Tag two");
        twoRe.setUserId(1L);
        twoRe.setTweetId(1L);
        mockReplies.add(oneRe);
        mockReplies.add(twoRe);
    }
/*
    @Test
    public void givenReplyObject_whenSaveReplies_AllReplies() {
        // Mock data
        String username = "james";
        Long tweetId = 1L;

        // Mock repository behavior
        given(replyRepository.saveAll(mockReplies)).willReturn(null);

        // Call the service method
        List<ReplyData> replyDataList = replyService.getAllRepliesByTweet(username, tweetId);

        // Verify that the saveAll method of the mock repository was called with the provided mock replies
        verify(replyRepository).saveAll(mockReplies);

        // Verify that the returned list of reply data has the correct size
        assertEquals(mockReplies.size(), replyDataList.size());
    }
*/
    @Test
    public void givenReplyObject_whenSavedReply_thenReturnReplyObject(){

        given(replyRepository.save(oneRe)).willReturn(oneRe);

        System.out.println(replyRepository);
        System.out.println(replyService);

        // when
        List<ReplyData> savedEmployee = replyService.getAllRepliesByTweet("Test", 1L);

        // then
        assertThat(savedEmployee).isNotNull();
    }

    @Test
    public void testReplyToTweet() {
        // Mock data
        String username = "james";
        Long tweetId = 1L;
        String replyContent = "Reply content";
        String tag = "Tag";

        // Mock tweet and user objects
        Tweet mockTweet = new Tweet();
        User mockUser = new User();
        mockUser.setUserId(1L);


        // Stubbing behavior of tweetService and authService
        when(tweetService.getTweetById(tweetId)).thenReturn(ResponseEntity.ok(mockTweet));
        when(authService.getUserByUsername(username)).thenReturn(ResponseEntity.ok(mockUser));

        // Mock reply object
        Reply reply = new Reply();
        reply.setReplyContent(replyContent);
        reply.setTag(tag);

        // Call the replyToTweet method
        ReplyData replyData = replyService.replyToTweet(username, tweetId, reply);

        // Verify that replyData is not null
        assertNotNull(replyData);
    }

}
