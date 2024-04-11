package com.cogent.twitter.backend.payload;

import com.cogent.twitter.backend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponse {
    private boolean error;
    private String message;
    private User user;
}