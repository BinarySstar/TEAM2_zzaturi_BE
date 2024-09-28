package goorm.zzaturi.domain.todo.dto;

import goorm.zzaturi.domain.todo.dto.response.TodoPaginationDto;
import java.util.List;
import lombok.Builder;

@Builder
public record TodoPaginationResponseWrapper(

    List<TodoPaginationDto> todos,

    boolean hasNext
) {

}
