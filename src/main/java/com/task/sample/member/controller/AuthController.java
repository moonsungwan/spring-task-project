package com.task.sample.member.controller;

import com.task.sample.common.entity.ApiResponseEntity;
import com.task.sample.member.dto.JwtTokenRequest;
import com.task.sample.member.dto.JwtTokenResponse;
import com.task.sample.member.dto.MemberRequest;
import com.task.sample.member.dto.MemberResponse;
import com.task.sample.member.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "AuthController", tags = "1. 인증 API")
@RequestMapping("/api/vi/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @ApiOperation(value = "회원가입")
    @PostMapping("/signup")
    public ApiResponseEntity<MemberResponse> signup(@RequestBody MemberRequest memberRequest) {
        return new ApiResponseEntity<>(authService.signup(memberRequest));
    }

    @ApiOperation(value = "로그인")
    @PostMapping("/login")
    public ApiResponseEntity<JwtTokenResponse> login(@RequestBody MemberRequest memberRequest) {
        return new ApiResponseEntity<>(authService.login(memberRequest));
    }

    @ApiOperation(value = "토큰재발급")
    @PostMapping("/reissue")
    public ApiResponseEntity<JwtTokenResponse> reissue(@RequestBody JwtTokenRequest jwtTokenRequest) {
        return new ApiResponseEntity<>(authService.reissue(jwtTokenRequest));
    }

}