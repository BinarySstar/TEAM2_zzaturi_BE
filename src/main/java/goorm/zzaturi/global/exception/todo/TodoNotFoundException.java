package goorm.zzaturi.global.exception.todo;

import goorm.zzaturi.global.exception.ZzaturiException;
import goorm.zzaturi.global.exception.dto.ErrorCode;

public class TodoNotFoundException extends ZzaturiException {

    public TodoNotFoundException() {
        super(ErrorCode.TODO_NOT_FOUND);
    }
}
