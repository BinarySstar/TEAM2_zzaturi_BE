package goorm.zzaturi.domain.comment.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CommentUpdateRequestDto(@NotNull(message = "내용을 입력하세요.") String content) {

}
