package goorm.zzaturi.domain.comment.dto.request;

import goorm.zzaturi.domain.member.entity.Member;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CommentCreateRequestDto(@NotNull(message = "댓글을 입력하세요.") String content,
                                      @NotNull(message = "존재하지 않는 회원입니다.") Member member) {

}