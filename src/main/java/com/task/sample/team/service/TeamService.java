package com.task.sample.team.service;

import com.task.sample.member.dto.MemberResponse;
import com.task.sample.team.dto.TeamRequest;
import com.task.sample.team.entity.Team;
import com.task.sample.team.entity.TeamMember;
import com.task.sample.team.repository.TeamRepository;
import com.task.sample.team.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class TeamService {

    private final UserRepository userRepository;

    private final TeamRepository teamRepository;

    @Transactional
    public MemberResponse save(TeamRequest teamRequest) {
        Team team = Team.builder()
                        .name(teamRequest.getName())
                        .build();
        teamRepository.save(team);

        teamRequest.getUsers().forEach(teamMember -> {
            team.getUsers().add(TeamMember.builder()
                                          .name(teamMember.getName())
                                          .age(teamMember.getAge())
                                          .build());
        });

        return null;
    }

}
