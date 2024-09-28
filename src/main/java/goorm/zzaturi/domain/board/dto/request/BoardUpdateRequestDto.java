package goorm.zzaturi.domain.board.dto.request;

import goorm.zzaturi.domain.board.entity.Category;

public record BoardUpdateRequestDto(Long id,
                                    String title,
                                    String content,
                                    String imageUrl,
                                    Category category) {
}
