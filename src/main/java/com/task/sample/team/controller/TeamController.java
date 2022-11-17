package com.task.sample.team.controller;

import com.task.sample.common.entity.ApiResponseEntity;
import com.task.sample.common.message.MessageCode;
import com.task.sample.team.dto.TeamRequest;
import com.task.sample.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/team")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public ApiResponseEntity<Boolean> save(@RequestBody TeamRequest teamRequest) {
        teamService.save(teamRequest);
        return new ApiResponseEntity<>(null, MessageCode.SUCCEED);
    }

}