package goorm.zzaturi.domain.todo.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import java.time.LocalDateTime;

public record TodoUpdateRequest(

    String title,

    String content,

    String importance,

    @FutureOrPresent(message = "마감일은 현재 시점 이후여야 합니다.")
    LocalDateTime deadLine
) {

}
