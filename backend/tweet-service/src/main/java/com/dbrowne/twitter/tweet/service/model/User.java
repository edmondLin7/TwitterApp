package com.dbrowne.twitter.tweet.service.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private Long userId;


    private String firstName;


    private String lastName;


    private String email;


    private String username;

    private String password;


    private String contactNumber;
}
