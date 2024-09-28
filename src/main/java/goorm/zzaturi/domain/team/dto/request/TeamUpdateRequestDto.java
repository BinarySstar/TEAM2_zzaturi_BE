package goorm.zzaturi.domain.team.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

public record TeamUpdateRequestDto(@NotNull(message = "팀 이름을 입력하세요.") String name,
                                   Boolean isPublic) {

    @Builder
    public TeamUpdateRequestDto(String name, Boolean isPublic) {
        this.name = name;
        this.isPublic = isPublic;
    }
}
