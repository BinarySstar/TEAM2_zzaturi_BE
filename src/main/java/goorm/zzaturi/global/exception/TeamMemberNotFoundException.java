package goorm.zzaturi.global.exception;

import goorm.zzaturi.global.exception.dto.ErrorCode;

public class TeamMemberNotFoundException extends ZzaturiException{
    public TeamMemberNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
