package com.task.sample.member.controller;

import com.task.sample.member.dto.JwtTokenRequest;
import com.task.sample.member.dto.JwtTokenResponse;
import com.task.sample.member.dto.MemberRequest;
import com.task.sample.member.dto.MemberResponse;
import com.task.sample.member.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vi/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<MemberResponse> signup(@RequestBody MemberRequest memberRequest) {
        return ResponseEntity.ok(authService.signup(memberRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenResponse> login(@RequestBody MemberRequest memberRequest) {
        return ResponseEntity.ok(authService.login(memberRequest));
    }

    @PostMapping("/reissue")
    public ResponseEntity<JwtTokenResponse> reissue(@RequestBody JwtTokenRequest jwtTokenRequest) {
        return ResponseEntity.ok(authService.reissue(jwtTokenRequest));
    }
}