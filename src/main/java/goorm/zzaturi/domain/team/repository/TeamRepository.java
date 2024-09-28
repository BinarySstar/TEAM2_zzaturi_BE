package goorm.zzaturi.domain.team.repository;

import goorm.zzaturi.domain.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    Optional<Team> findByInviteCode(String inviteCode);
    Optional<Team> findByName(String name);
}
