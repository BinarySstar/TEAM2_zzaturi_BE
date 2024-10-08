package goorm.zzaturi.global.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    MEMBER_NOT_FOUND(404, "존재하지 않는 멤버입니다.");

    private final int status;
    private final String message;
    }
