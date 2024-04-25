package com.dbrowne.twitter.auth.service.controller;

import com.dbrowne.twitter.auth.service.entity.User;
import com.dbrowne.twitter.auth.service.payload.LoginDto;
import com.dbrowne.twitter.auth.service.payload.LoginResponse;
import com.dbrowne.twitter.auth.service.payload.RegisterDto;
import com.dbrowne.twitter.auth.service.payload.RegisterResponse;
import com.dbrowne.twitter.auth.service.security.JwtTokenProvider;
import com.dbrowne.twitter.auth.service.service.AuthService;
import java.time.LocalDateTime;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WithMockUser(username="admin",roles="ADMIN")
@WebMvcTest
public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthService authService;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @Test
    public void givenValidLoginDto_whenLogin_thenReturnsAccepted() throws Exception {
        // given
        LoginDto loginDto = new LoginDto("username", "password");
        LoginResponse mockResponse = new LoginResponse(false, "Success"); // Assuming login is successful

        given(authService.login(loginDto)).willReturn(mockResponse);

        // when
        ResultActions result = mockMvc.perform(post("/api/v1.0/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .with(csrf())
                .content(objectMapper.writeValueAsString(loginDto)));

        // then
        result.andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error", is(false)))
                .andExpect(jsonPath("$.message", is("Success")));
    }

    @Test
    public void givenInvalidLoginDto_whenLogin_thenReturnsUnauthorized() throws Exception {
        // given
        LoginDto loginDto = new LoginDto("invalidUsername", "invalidPassword");
        LoginResponse mockResponse = new LoginResponse(true, "Username or password is incorrect");

        given(authService.login(loginDto)).willReturn(mockResponse);

        // when
        ResultActions result = mockMvc.perform(post("/api/v1.0/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .with(csrf())
                .content(objectMapper.writeValueAsString(loginDto)));

        // then
        result.andExpect(status().isUnauthorized())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error", is(true)))
                .andExpect(jsonPath("$.message", is("Username or password is incorrect")));
    }

    @Test
    public void givenValidRegisterDto_whenRegister_thenReturnsAccepted() throws Exception {
        // Given
        RegisterDto registerDto = new RegisterDto();
        registerDto.setFirstName("John");
        registerDto.setLastName("Doe");
        registerDto.setUsername("johndoe");
        registerDto.setEmail("john@example.com");
        registerDto.setPassword("password");
        registerDto.setContactNumber("1234567890");

        RegisterResponse mockResponse = new RegisterResponse(false, "Success");
        given(authService.register(any(RegisterDto.class))).willReturn(mockResponse);

        // When
        ResultActions result = mockMvc.perform(post("/api/v1.0/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                .content(asJsonString(registerDto)));

        // Then
        result.andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value(false))
                .andExpect(jsonPath("$.message").value("Success"));
    }

    // Helper method to convert object to JSON string
    private String asJsonString(Object object) throws Exception {
        return objectMapper.writeValueAsString(object);
    }

    @Test
    public void givenValidRequest_whenGetAllUsers_thenReturnsUsers() throws Exception {
        // Given
        List<User> users = new ArrayList<>();
        // Add some sample users to the list
        users.add(new User("user1"));
        users.add(new User("user2"));

        given(authService.getAllUsers()).willReturn(users);

        // When
        ResultActions result = mockMvc.perform(get("/api/v1.0/auth/users")
                .contentType(MediaType.APPLICATION_JSON)
                .with(csrf())
        );

        // Then
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].username").value("user1"))
                .andExpect(jsonPath("$[1].username").value("user2"));
    }

}
