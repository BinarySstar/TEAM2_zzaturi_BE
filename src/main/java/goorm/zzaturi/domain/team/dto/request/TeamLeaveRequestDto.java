package goorm.zzaturi.domain.team.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

public record TeamLeaveRequestDto(@NotNull(message = "팀을 입력해주세요.") String teamName,
                                  @NotNull(message = "유효하지 않는 회원입니다.") String email){

    @Builder
    public TeamLeaveRequestDto(String teamName, String email) {
        this.teamName = teamName;
        this.email = email;
    }
}