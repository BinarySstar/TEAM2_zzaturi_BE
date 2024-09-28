package goorm.zzaturi.domain.board.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record BoardUpdateRequestDto(

        @NotNull(message = "제목을 입력해주세요.")
        String title,

        @NotNull(message = "내용을 입력해주세요.")
        String content,

        String imageUrl,

        @NotNull(message = "유효한 카테고리가 아닙니다.")
        String category)
{
}
