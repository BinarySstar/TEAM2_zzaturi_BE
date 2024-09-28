package goorm.zzaturi.global.exception.member;

import goorm.zzaturi.global.exception.ZzaturiException;
import goorm.zzaturi.global.exception.dto.ErrorCode;

public class MemberNotFoundException extends ZzaturiException {

    public MemberNotFoundException() {
        super(ErrorCode.MEMBER_NOT_FOUND);
    }
}
