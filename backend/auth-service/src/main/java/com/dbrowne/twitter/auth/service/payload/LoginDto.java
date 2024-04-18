package com.dbrowne.twitter.auth.service.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    private String usernameOrEmail;
    private String password;
}