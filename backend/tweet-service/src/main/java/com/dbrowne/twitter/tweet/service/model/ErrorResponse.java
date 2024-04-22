package com.dbrowne.twitter.tweet.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponse {

    private String errorMessage;
    private String errorCode;
}
