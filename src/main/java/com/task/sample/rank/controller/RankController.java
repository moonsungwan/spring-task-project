package com.task.sample.rank.controller;

import com.task.sample.common.entity.ApiResponseEntity;
import com.task.sample.common.message.MessageCode;
import com.task.sample.rank.dto.RankRequest;
import com.task.sample.rank.dto.RankResponse;
import com.task.sample.rank.service.RankService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "RankController", tags = "3. 인기검색어 랭킹 API")
@RequestMapping("/api/vi")
@RequiredArgsConstructor
public class RankController {

    private final RankService rankService;

    @ApiOperation(value = "인기 검색어 목록")
    @GetMapping("/rank")
    public ApiResponseEntity<List<RankResponse>> getRank() {
        return new ApiResponseEntity<>(rankService.getRank(), MessageCode.SUCCEED);
    }

    @ApiOperation(value = "인기 검색어 입력")
    @PostMapping("/rank")
    public ApiResponseEntity<String> increaseKeyword(@RequestBody RankRequest rankRequest) {
        return new ApiResponseEntity<>(rankService.increaseKeyword(rankRequest), MessageCode.SUCCEED);
    }

}