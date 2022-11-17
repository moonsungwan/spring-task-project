package com.task.sample.team.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TeamResponse {

    private String name;

    private List<TeamMemberRequest> teamMembers;

}