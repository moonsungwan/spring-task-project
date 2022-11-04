package com.task.sample.common.redis.lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.concurrent.TimeUnit;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class RLockAspect {

    private final RedissonClient redissonClient;

    @Around("@annotation(distributedLock)")
    public Object executeWithLock(ProceedingJoinPoint joinPoint, DistributedLock distributedLock) throws Throwable {
        final String lockName = getLockName(joinPoint, distributedLock);
        RLock lock = redissonClient.getLock(lockName);
        tryLock(lock, lockName, 3L, 2L, TimeUnit.SECONDS);
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            throw e;
        } finally {
            releaseLock(lock, lockName);
        }
        return result;
    }

    private void tryLock(RLock rLock, String lockName, Long waitTime, Long leaseTime, TimeUnit timeUnit) {
        boolean isAcquiredLock;

        try {
            isAcquiredLock = rLock.tryLock(waitTime, leaseTime, timeUnit);
        } catch (InterruptedException e) {
            throw new RuntimeException("결제중 오류가 발생했습니다.");
        }

        if (!isAcquiredLock) {
            throw new RuntimeException("일시적 오류. 잠시 후에 다시 시도해주세요.");
        }

        log.info("Try Lock. key: {}", lockName);
    }

    private void releaseLock(RLock rLock, String lockName) {
        if (rLock.isLocked() && rLock.isHeldByCurrentThread()) {
            log.info("Release Lock. key: {}", lockName);
            rLock.unlock();
            return;
        }
        log.info("Already Release Lock. key: {}", lockName);
    }

    private String getLockName(ProceedingJoinPoint joinPoint, DistributedLock distributedLock) {
        if (ObjectUtils.isEmpty(joinPoint.getArgs())) {
            throw new IllegalArgumentException("적용하려는 메서드의 인자가 존재하지 않습니다.");
        }
        RLockCase lockCase = distributedLock.lockCase();
        return lockCase.name() + "_" + lockCase.getKeyType().getKey();
    }

}
