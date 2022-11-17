package com.task.sample.team.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.task.sample.team.dto.TeamRequest;
import com.task.sample.team.dto.TeamResponse;
import com.task.sample.team.entity.Team;
import com.task.sample.team.entity.TeamMember;
import com.task.sample.team.repository.TeamRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    
    private final ModelMapper modelMapper;
    
    @Transactional
    public TeamResponse save(TeamRequest teamRequest) {
        Team team = Team.builder()
                        .name(teamRequest.getName())
                        .build();
        
        teamRequest.getTeamMembers().forEach(teamMember -> {
        	team.addTeamMember(TeamMember.builder()
        								 .name(teamMember.getName())
        								 .build());
        });
        
        teamRepository.save(team);
        
        return modelMapper.map(team, TeamResponse.class);
    }

}
