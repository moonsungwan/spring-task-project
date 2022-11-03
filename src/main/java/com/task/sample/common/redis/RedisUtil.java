package com.task.sample.common.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisUtil {

    private HashOperations<String, String, Object> hashOperations;

    private final RedisTemplate redisTemplate;

    @PostConstruct
    public void init() {
        this.hashOperations = redisTemplate.opsForHash();
    }

    public void incrementScore(String key, String value) {
        Double score = 0.0;
        try {
            redisTemplate.opsForZSet().incrementScore(key, value,1);
        } catch (Exception e) {
            log.error(e.toString());
        }
        redisTemplate.opsForZSet().incrementScore(key, value, score);
    }

    public Set<ZSetOperations.TypedTuple<String>> reverseRangeWithScores(String key) {
        ZSetOperations<String, String> ZSetOperations = redisTemplate.opsForZSet();
        return ZSetOperations.reverseRangeWithScores(key, 0, 10);
    }
}
