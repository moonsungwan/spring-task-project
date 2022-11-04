package com.task.sample.member.controller;

import com.task.sample.common.entity.ApiResponseEntity;
import com.task.sample.member.dto.MemberResponse;
import com.task.sample.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vi/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/me")
    public ApiResponseEntity<MemberResponse> getMyMemberInfo() {
        return new ApiResponseEntity<>(memberService.getMyInfo());
    }

    @GetMapping("/{email}")
    public ApiResponseEntity<MemberResponse> getMemberInfo(@PathVariable String email) {
        return new ApiResponseEntity<>(memberService.getMemberInfo(email));
    }

}