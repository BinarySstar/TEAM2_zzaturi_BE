package goorm.zzaturi.global.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    MEMBER_NOT_FOUND(404, "존재하지 않는 멤버입니다."),
    BOARD_NOT_FOUND(404, "존재하지 않는 게시글입니다."),
    COMMENT_NOT_FOUND(404, "존재하지 않는 댓글입니다."),
    LIKE_NOT_FOUND(404, "존재하지 않는 좋아요입니다."),
    ALREADY_LIKED(409, "이미 좋아요를 누른 게시글입니다.");

    private final int status;
    private final String message;
    }
