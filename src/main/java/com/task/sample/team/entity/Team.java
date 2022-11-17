package com.task.sample.team.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TEAM")
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    // 양방향 관계일 때만 설정함
    @OneToMany(mappedBy = "team")
    private List<TeamMember> users = new ArrayList<>();

    @Builder
    public Team(String name) {
        this.name = name;
    }

}
