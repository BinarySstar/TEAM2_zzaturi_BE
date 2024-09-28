package goorm.zzaturi.domain.board.dto.request;

import goorm.zzaturi.domain.board.entity.Category;
import goorm.zzaturi.domain.member.entity.Member;
import lombok.Builder;

public record BoardCreateRequestDto(String title,
                                    String content,
                                    String imageUrl,
                                    Member member,
                                    Category category) {

    @Builder
    public BoardCreateRequestDto(String title, String content, String imageUrl, Member member, Category category) {
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.member = member;
        this.category = category;
    }
}