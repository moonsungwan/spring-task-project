package com.task.sample.auth.controller;

import com.task.sample.common.entity.ApiResponseEntity;
import com.task.sample.auth.dto.JwtTokenRequest;
import com.task.sample.auth.dto.JwtTokenResponse;
import com.task.sample.member.dto.MemberRequest;
import com.task.sample.member.dto.MemberResponse;
import com.task.sample.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ApiResponseEntity<MemberResponse> signup(@RequestBody MemberRequest memberRequest) {
        return new ApiResponseEntity<>(authService.signup(memberRequest));
    }

    @PostMapping("/login")
    public ApiResponseEntity<JwtTokenResponse> login(@RequestBody MemberRequest memberRequest) {
        return new ApiResponseEntity<>(authService.login(memberRequest));
    }

    @PostMapping("/reissue")
    public ApiResponseEntity<JwtTokenResponse> reissue(@RequestBody JwtTokenRequest jwtTokenRequest) {
        return new ApiResponseEntity<>(authService.reissue(jwtTokenRequest));
    }

}