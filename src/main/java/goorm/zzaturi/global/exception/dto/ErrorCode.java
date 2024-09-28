package goorm.zzaturi.global.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    MEMBER_NOT_FOUND(404, "존재하지 않는 멤버입니다."),
    BOARD_NOT_FOUND(404, "존재하지 않는 게시글입니다."),
    COMMENT_NOT_FOUND(404, "존재하지 않는 댓글입니다.");
    MEMBER_NOT_FOUND(404, "존재하지 않는 멤버입니다.");

    private final int status;
    private final String message;
    }
