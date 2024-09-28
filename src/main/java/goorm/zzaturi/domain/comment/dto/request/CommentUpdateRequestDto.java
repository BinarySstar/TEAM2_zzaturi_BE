package goorm.zzaturi.domain.comment.dto.request;

import lombok.Builder;

public record CommentUpdateRequestDto(String content) {

    @Builder
    public CommentUpdateRequestDto(String content) {
        this.content = content;
    }

}
