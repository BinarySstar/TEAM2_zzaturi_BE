package goorm.zzaturi.domain.like.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record LikeRequestDto(

        @NotNull(message = "유효한 회원이 아닙니다.")
        String email,

        @NotNull(message = "유효한 게시글이 아닙니다.")
        Long boardId) {

}
