package com.dbrowne.twitter.auth.service.payload;

import com.dbrowne.twitter.auth.service.entity.User;
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

    public RegisterResponse(boolean error, String success) {
        this.error = error;
        this.message = success;
    }
}