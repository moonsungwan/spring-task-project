package com.task.sample.rank.controller;

import com.task.sample.common.entity.ApiResponseEntity;
import com.task.sample.common.message.MessageCode;
import com.task.sample.rank.dto.RankRequest;
import com.task.sample.rank.dto.RankResponse;
import com.task.sample.rank.service.RankService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vi")
@RequiredArgsConstructor
public class RankController {

    private final RankService rankService;

    @GetMapping("/rank")
    public ApiResponseEntity<List<RankResponse>> getRank() {
        return new ApiResponseEntity<>(rankService.getRank(), MessageCode.SUCCEED);
    }

    @PostMapping("/rank")
    public ApiResponseEntity<String> increaseKeyword(@RequestBody RankRequest rankRequest) {
        return new ApiResponseEntity<>(rankService.increaseKeyword(rankRequest), MessageCode.SUCCEED);
    }

}