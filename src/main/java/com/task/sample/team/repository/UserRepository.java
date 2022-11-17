package com.task.sample.team.repository;

import com.task.sample.team.entity.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<TeamMember, Long> {

}
