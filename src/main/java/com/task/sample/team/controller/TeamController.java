package com.task.sample.team.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.sample.common.entity.ApiResponseEntity;
import com.task.sample.common.message.MessageCode;
import com.task.sample.team.dto.TeamRequest;
import com.task.sample.team.dto.TeamResponse;
import com.task.sample.team.service.TeamService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/team")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public ApiResponseEntity<TeamResponse> save(@RequestBody TeamRequest teamRequest) {
        return new ApiResponseEntity<>(teamService.save(teamRequest), MessageCode.SUCCEED);
    }

}