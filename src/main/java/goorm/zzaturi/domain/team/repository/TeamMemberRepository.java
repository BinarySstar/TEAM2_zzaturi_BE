package goorm.zzaturi.domain.team.repository;

import goorm.zzaturi.domain.member.entity.Member;
import goorm.zzaturi.domain.team.entity.Team;
import goorm.zzaturi.domain.team.entity.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {

    Optional<TeamMember> findByTeamAndMember(Team team, Member member);
}
