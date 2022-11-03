package com.task.sample.rank.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RankResponse {

    private String keyword;

    private int count;

    @Builder
    public RankResponse(String keyword, int count) {
        this.keyword = keyword;
        this.count = count;
    }
}