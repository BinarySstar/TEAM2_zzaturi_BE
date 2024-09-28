package goorm.zzaturi.global.exception;

import goorm.zzaturi.global.exception.dto.ErrorCode;

public class MemberNotFoundException extends ZzaturiException{
    public MemberNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
