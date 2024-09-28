package goorm.zzaturi.domain.comment.dto.response;

import goorm.zzaturi.domain.board.entity.Board;
import goorm.zzaturi.domain.comment.entity.Comment;
import lombok.Builder;
import goorm.zzaturi.domain.member.entity.Member;

import java.time.LocalDate;
import java.util.List;

@Builder
public record CommentResponseDto(Long id,
                                 String content,
                                 Member member,
                                 Board board,
                                 LocalDate createdAt) {

    public static CommentResponseDto of(Comment comment) {
        return CommentResponseDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .member(comment.getMember())
                .board(comment.getBoard())
                .createdAt(comment.getCreatedAt())
                .build();
    }

    public static List<CommentResponseDto> listOf(List<Comment> comments) {
        return comments.stream()
                .map(CommentResponseDto::of)
                .toList();
    }
}
