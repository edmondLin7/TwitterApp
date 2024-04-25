package com.dbrowne.replyservice.controller;

import java.time.LocalDateTime;
import java.util.*;
import com.dbrowne.replyservice.model.ReplyData;
import com.dbrowne.replyservice.entity.Reply;
import com.dbrowne.replyservice.service.ReplyService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class ReplyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ReplyService replyService;
    @Test
    public void givenValidUsernameAndTweetId_whenGetAllReplies_thenReturnsReplies() throws Exception {
        // given
        String username = "testUser";
        Long tweetId = 123L;
        List<ReplyData> mockReplies = createMockReplies(); // Create mock reply data

        given(replyService.getAllRepliesByTweet(username, tweetId))
                .willReturn(mockReplies);

        // then
        ResultActions result = mockMvc.perform(get("/api/v1.0/replies/{username}/{tweetId}", username, tweetId));

        result.andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()", is(mockReplies.size())))
                .andExpect(jsonPath("$[0].replyId", is(mockReplies.get(0).getReplyId().intValue())))
                .andExpect(jsonPath("$[0].replyContent", is(mockReplies.get(0).getReplyContent())))
                .andExpect(jsonPath("$[0].tag", is(mockReplies.get(0).getTag())))
                .andExpect(jsonPath("$[1].replyId", is(mockReplies.get(1).getReplyId().intValue())))
                .andExpect(jsonPath("$[1].replyContent", is(mockReplies.get(1).getReplyContent())))
                .andExpect(jsonPath("$[1].tag", is(mockReplies.get(1).getTag())));
    }

    public static List<ReplyData> createMockReplies() {
        List<ReplyData> mockReplies = new ArrayList<>();

        // Create mock replies
        ReplyData reply1 = new ReplyData();
        reply1.setReplyId(1L);
        reply1.setReplyContent("Reply 1 content");
        reply1.setTag("Tag 1");
        reply1.setTimestamp(LocalDateTime.now());
        reply1.setLikeCount(0L);

        ReplyData reply2 = new ReplyData();
        reply2.setReplyId(2L);
        reply2.setReplyContent("Reply 2 content");
        reply2.setTag("Tag 2");
        reply2.setTimestamp(LocalDateTime.now());
        reply2.setLikeCount(0L);

        // Add mock replies to the list
        mockReplies.add(reply1);
        mockReplies.add(reply2);

        return mockReplies;
    }

    @Test
    public void givenValidUsernameAndTweetId_whenReplyToTweet_thenReturnsCreated() throws Exception {
        // given
        String username = "testUser";
        Long tweetId = 123L;
        Reply mockReply = createMockReply(); // Create a mock reply object

        ReplyData mockReplyData = new ReplyData();
        mockReplyData.setReplyId(1L);
        mockReplyData.setReplyContent("Reply content");
        mockReplyData.setTimestamp(LocalDateTime.now());

        given(replyService.replyToTweet(username, tweetId, mockReply))
                .willReturn(mockReplyData);

        // when
        ResultActions result = mockMvc.perform(post("/api/v1.0/replies/{username}/{tweetId}", username, tweetId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockReply)));

        // then
        result.andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.replyId", is(mockReplyData.getReplyId().intValue())))
                .andExpect(jsonPath("$.replyContent", is(mockReplyData.getReplyContent())))
                .andExpect(jsonPath("$.timestamp").exists()); // Add more assertions as needed
    }

    private Reply createMockReply() {
        Reply reply = new Reply();
        reply.setReplyContent("Reply content");
        // Set other properties of the reply as needed
        return reply;
    }
}
