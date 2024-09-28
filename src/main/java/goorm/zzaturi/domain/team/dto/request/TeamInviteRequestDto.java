package goorm.zzaturi.domain.team.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record TeamInviteRequestDto(String inviteCode,
                                   @NotBlank(message = "이메일을 입력해주세요.") String email) {

}
