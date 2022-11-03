package com.task.sample.member.service;

import com.task.sample.common.exception.CustomException;
import com.task.sample.common.message.MessageCode;
import com.task.sample.security.util.SecurityUtil;
import com.task.sample.member.dto.MemberResponse;
import com.task.sample.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public MemberResponse getMemberInfo(String email) {
        return memberRepository.findByEmail(email)
                               .map(MemberResponse::of)
                               .orElseThrow(() -> new CustomException(MessageCode.INVALID_MEMBER));
    }

    // 현재 SecurityContext 에 있는 유저 정보 가져오기
    @Transactional(readOnly = true)
    public MemberResponse getMyInfo() {
        return memberRepository.findById(SecurityUtil.getCurrentMemberId())
                               .map(MemberResponse::of)
                               .orElseThrow(() -> new CustomException(MessageCode.INVALID_MEMBER));
    }
}
