package com.task.sample.team.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TeamRequest {

    private String name;

    private List<TeamMemberRequest> teamMembers;

}