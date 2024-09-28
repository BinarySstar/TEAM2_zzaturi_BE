package goorm.zzaturi.domain.comment.dto.request;

import goorm.zzaturi.domain.member.entity.Member;
import lombok.Builder;

public record CommentCreateRequestDto(String content,
                                      Member member) {

    @Builder
    public CommentCreateRequestDto(String content, Member member) {
        this.content = content;
        this.member = member;
    }
}
