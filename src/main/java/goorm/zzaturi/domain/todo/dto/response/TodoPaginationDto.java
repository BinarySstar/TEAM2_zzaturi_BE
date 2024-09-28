package goorm.zzaturi.domain.todo.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record TodoPaginationDto(

    Long todoId,

    String title,

    String content,

    boolean completed,

    String importance,

    LocalDateTime deadLine,

    LocalDateTime completedAt,

    LocalDateTime createdAt
) {

}
