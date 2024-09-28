package goorm.zzaturi.global.exception;

import goorm.zzaturi.global.exception.dto.ErrorCode;

public class BoardNotFoundException extends ZzaturiException {
    public BoardNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
