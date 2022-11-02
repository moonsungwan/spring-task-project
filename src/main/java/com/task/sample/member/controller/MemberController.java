package com.task.sample.member.controller;

import com.task.sample.member.MemberLoginRequest;
import com.task.sample.member.service.MemberService;
import com.task.sample.security.TokenInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vi/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/login")
    public TokenInfo login(@RequestBody MemberLoginRequest memberLoginRequest) {
        TokenInfo tokenInfo = memberService.login(memberLoginRequest);
        return tokenInfo;
    }
}