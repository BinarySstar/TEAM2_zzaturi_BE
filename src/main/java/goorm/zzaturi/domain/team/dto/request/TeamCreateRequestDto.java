package goorm.zzaturi.domain.team.dto.request;

import goorm.zzaturi.domain.member.entity.Member;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

public record TeamCreateRequestDto(
        @NotNull(message = "팀 이름을 입력해주세요.") String name,
        @NotNull(message = "유효한 회원이 아닙니다.") Member leader,
        Boolean isPublic) {

    @Builder
    public TeamCreateRequestDto(String name, Member leader, Boolean isPublic) {
        this.name = name;
        this.leader = leader;
        this.isPublic = isPublic;
    }
}
