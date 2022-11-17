package com.task.sample.auth.service;

import com.task.sample.common.exception.CustomException;
import com.task.sample.common.message.MessageCode;
import com.task.sample.auth.dto.JwtTokenRequest;
import com.task.sample.member.dto.MemberRequest;
import com.task.sample.member.dto.MemberResponse;
import com.task.sample.member.entity.Member;
import com.task.sample.member.repository.MemberRepository;
import com.task.sample.security.jwt.JwtTokenProvider;
import com.task.sample.auth.dto.JwtTokenResponse;
import com.task.sample.security.entity.RefreshToken;
import com.task.sample.security.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public MemberResponse signup(MemberRequest memberRequest) {
        if (memberRepository.existsByEmail(memberRequest.getEmail())) {
            throw new CustomException(MessageCode.EXISTING_MEMBER);
        }

        Member member = memberRequest.toMember(passwordEncoder);
        return MemberResponse.of(memberRepository.save(member));
    }

    @Transactional
    public JwtTokenResponse login(MemberRequest memberRequest) {
        if (!memberRepository.existsByEmail(memberRequest.getEmail())) {
            throw new CustomException(MessageCode.INVALID_MEMBER);
        }

        UsernamePasswordAuthenticationToken authenticationToken = memberRequest.toAuthentication();

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        JwtTokenResponse jwtTokenResponse = jwtTokenProvider.generateTokenResponse(authentication);

        RefreshToken refreshToken = RefreshToken.builder()
                                                .key(authentication.getName())
                                                .value(jwtTokenResponse.getRefreshToken())
                                                .build();

        refreshTokenRepository.save(refreshToken);

        return jwtTokenResponse;
    }

    @Transactional
    public JwtTokenResponse reissue(JwtTokenRequest jwtTokenRequest) {
        // 1. Refresh Token 검증
        if (!jwtTokenProvider.validateToken(jwtTokenRequest.getRefreshToken())) {
            throw new CustomException(MessageCode.INVALID_AUTH_REFRESH_TOKEN);
        }

        Authentication authentication = jwtTokenProvider.getAuthentication(jwtTokenRequest.getAccessToken());

        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                                                          .orElseThrow(() -> new CustomException(MessageCode.ALREADY_LOGOUT));

        if (!refreshToken.getValue().equals(jwtTokenRequest.getRefreshToken())) {
            throw new CustomException(MessageCode.INVALID_AUTH_TOKEN);
        }

        JwtTokenResponse jwtTokenResponse = jwtTokenProvider.generateTokenResponse(authentication);

        RefreshToken newRefreshToken = refreshToken.updateValue(jwtTokenResponse.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        // 토큰 발급
        return jwtTokenResponse;
    }
}
