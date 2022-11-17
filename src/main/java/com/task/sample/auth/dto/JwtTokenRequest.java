package com.task.sample.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JwtTokenRequest {

    private String accessToken;

    private String refreshToken;

}
