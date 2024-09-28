package goorm.zzaturi.domain.comment.dto.response;

import goorm.zzaturi.domain.board.entity.Board;
import goorm.zzaturi.domain.comment.entity.Comment;
import lombok.Builder;
import goorm.zzaturi.domain.member.entity.Member;

import java.time.LocalDate;

public record CommentCreateResponseDto(Long id,
                                       String content,
                                       Member member,
                                       Board board,
                                       LocalDate createdAt) {

    @Builder
    public CommentCreateResponseDto(Long id, String content, Member member, Board board, LocalDate createdAt) {
        this.id = id;
        this.content = content;
        this.member = member;
        this.board = board;
        this.createdAt = createdAt;
    }

    public static CommentCreateResponseDto of(Comment comment) {
        return CommentCreateResponseDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .member(comment.getMember())
                .board(comment.getBoard())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
