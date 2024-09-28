package goorm.zzaturi.domain.board.dto.request;

import goorm.zzaturi.domain.board.entity.Category;
import lombok.Builder;

public record BoardUpdateRequestDto(String title,
                                    String content,
                                    String imageUrl,
                                    Category category) {

    @Builder
    public BoardUpdateRequestDto(String title, String content, String imageUrl, Category category) {
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.category = category;
    }
}
