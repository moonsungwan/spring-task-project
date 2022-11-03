package com.task.sample.member.controller;

import com.task.sample.common.entity.ApiResponseEntity;
import com.task.sample.member.dto.MemberResponse;
import com.task.sample.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "MemberController", tags = "2. 내 정보 API")
@RequestMapping("/api/vi/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @ApiOperation(value = "내 정보 조회")
    @GetMapping("/me")
    public ApiResponseEntity<MemberResponse> getMyMemberInfo() {
        return new ApiResponseEntity<>(memberService.getMyInfo());
    }

    @ApiOperation(value = "이메일 조회")
    @GetMapping("/{email}")
    public ApiResponseEntity<MemberResponse> getMemberInfo(@PathVariable String email) {
        return new ApiResponseEntity<>(memberService.getMemberInfo(email));
    }

}