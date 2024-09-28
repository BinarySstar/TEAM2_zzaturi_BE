package goorm.zzaturi.domain.board.dto.request;

import goorm.zzaturi.domain.board.entity.Category;
import goorm.zzaturi.domain.member.entity.Member;

public record BoardUpdateRequestDto(Long id,
                                    String title,
                                    String content,
                                    String imageUrl,
                                    Member member,
                                    Category category) {
}
