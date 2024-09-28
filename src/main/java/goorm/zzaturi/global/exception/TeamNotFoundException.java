package goorm.zzaturi.global.exception;

import goorm.zzaturi.global.exception.dto.ErrorCode;

public class TeamNotFoundException extends ZzaturiException {
    public TeamNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
