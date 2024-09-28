package goorm.zzaturi.domain.team.dto.response;

import goorm.zzaturi.domain.member.entity.Member;
import goorm.zzaturi.domain.team.entity.Team;
import lombok.Builder;

import java.util.List;

public record TeamResponseDto(Long id,
                              String name,
                              Member leader,
                              Boolean isPublic) {

    @Builder
    public TeamResponseDto(Long id, String name, Member leader, Boolean isPublic) {
        this.id = id;
        this.name = name;
        this.leader = leader;
        this.isPublic = isPublic;
    }

    public static TeamResponseDto of(Team team) {
        return TeamResponseDto.builder()
                .id(team.getId())
                .name(team.getName())
                .leader(team.getLeader())
                .isPublic(team.getIsPublic())
                .build();
    }

    public static List<TeamResponseDto> listOf(List<Team> teams) {
        return teams.stream()
                .map(TeamResponseDto::of)
                .toList();
    }
}
