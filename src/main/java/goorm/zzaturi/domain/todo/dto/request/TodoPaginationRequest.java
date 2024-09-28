package goorm.zzaturi.domain.todo.dto.request;

public record TodoPaginationRequest(

    Long lastTodoId,

    Long teamId
) {

}
