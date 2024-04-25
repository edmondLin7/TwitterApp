package com.dbrowne.replyservice.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long userId;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private String password;

    private String contactNumber;
}


