package goorm.zzaturi.domain.like.dto;

import goorm.zzaturi.domain.board.entity.Board;
import goorm.zzaturi.domain.member.entity.Member;
import lombok.Builder;

public record LikeRequestDto(Member member, Board board) {

    @Builder
    public LikeRequestDto(Member member, Board board) {
        this.member = member;
        this.board = board;
    }
}
