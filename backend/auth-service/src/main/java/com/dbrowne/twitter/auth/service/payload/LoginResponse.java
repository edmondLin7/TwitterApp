package com.dbrowne.twitter.auth.service.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private boolean error;
    private String message;
    private String token;

    public LoginResponse(boolean error, String message) {
        this.error = error;
        this.message = message;
    }
}