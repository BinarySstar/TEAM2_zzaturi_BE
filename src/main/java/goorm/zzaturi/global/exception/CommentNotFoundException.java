package goorm.zzaturi.global.exception;

import goorm.zzaturi.global.exception.dto.ErrorCode;

public class CommentNotFoundException extends ZzaturiException {
    public CommentNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
