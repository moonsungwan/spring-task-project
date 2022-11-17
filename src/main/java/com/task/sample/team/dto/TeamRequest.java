package com.task.sample.team.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class TeamRequest {

    private String name;

    private List<UserRequest> users;

}