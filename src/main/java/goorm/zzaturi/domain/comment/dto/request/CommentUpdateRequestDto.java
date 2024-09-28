package goorm.zzaturi.domain.comment.dto.request;

import lombok.Builder;

public record CommentUpdateRequestDto(Long id,
                                      String content) {

    @Builder
    public CommentUpdateRequestDto(Long id, String content) {
        this.id = id;
        this.content = content;
    }

}
