package com.task.sample.rank.service;

import com.task.sample.common.redis.cache.RedisCacheKey;
import com.task.sample.common.redis.cache.RedisUtil;
import com.task.sample.rank.dto.RankRequest;
import com.task.sample.rank.dto.RankResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RankService {

    private final RedisUtil redisUtil;

    public List<RankResponse> getRank() {
        List<RankResponse> topKeywords = Objects.requireNonNull(redisUtil.reverseRangeWithScores(RedisCacheKey.RANKS))
                                                .stream()
                                                .map(it -> convert(it.getValue(), it.getScore()))
                                                .collect(Collectors.toList());

        return topKeywords;
    }

    public String increaseKeyword(RankRequest rankRequest) {
        redisUtil.incrementScore(RedisCacheKey.RANKS, rankRequest.getKeyword());
        return rankRequest.getKeyword();
    }

    private RankResponse convert(String keyword, Double score) {
        int count = Objects.requireNonNull(score).intValue();
        return RankResponse.builder()
                           .keyword(keyword)
                           .count(count)
                           .build();
    }

}
