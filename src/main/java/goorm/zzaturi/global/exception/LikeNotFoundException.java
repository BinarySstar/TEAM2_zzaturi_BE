package goorm.zzaturi.global.exception;

import goorm.zzaturi.global.exception.dto.ErrorCode;

public class LikeNotFoundException extends ZzaturiException {
    public LikeNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
