package com.task.sample.common.redis.lock;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum LockCase {

    MEMBER_POINT(LockKeyType.AUTHORIZATION_HEADER);

    @Getter
    private final LockKeyType keyType;

}