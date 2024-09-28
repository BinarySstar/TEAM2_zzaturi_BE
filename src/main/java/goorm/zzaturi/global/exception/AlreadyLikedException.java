package goorm.zzaturi.global.exception;

import goorm.zzaturi.global.exception.dto.ErrorCode;

public class AlreadyLikedException extends ZzaturiException{
    public AlreadyLikedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
