package goorm.zzaturi.domain.board.dto.request;

import goorm.zzaturi.domain.board.entity.Category;
import goorm.zzaturi.domain.member.entity.Member;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record BoardCreateRequestDto(
        @NotNull(message = "제목을 입력해주세요.")
        String title,

        @NotNull(message = "내용을 입력해주세요.")
        String content,
        String imageUrl,

        @NotNull(message = "유효한 회원이 아닙니다.")
        Member member,

        @NotNull(message = "유효한 카테고리가 아닙니다.")
        Category category) {
    
}