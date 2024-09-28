package goorm.zzaturi.global.exception;

import goorm.zzaturi.global.exception.dto.ErrorCode;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class ZzaturiException extends RuntimeException {

    private final Map<String, String> validation = new HashMap<>();

    private final ErrorCode errorCode;
}
