package com.task.sample.common.redis.lock;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RLockCase {

    MEMBER_POINT(RLockKeyType.AUTHORIZATION_HEADER);

    @Getter
    private final RLockKeyType keyType;

}