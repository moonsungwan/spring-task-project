package com.task.sample.common.redis.lock;

import com.task.sample.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.function.Function;

@RequiredArgsConstructor
public enum LockKeyType {

    AUTHORIZATION_HEADER(request -> {
        return String.valueOf(SecurityUtil.getCurrentMemberId());
    });

    private final Function<HttpServletRequest, String> findKey;

    public String getKey() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        return findKey.apply(request);
    }
}