package goorm.zzaturi.domain.todo.dto.request;


import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public record TodoCreateRequest(

    @NotEmpty(message = "제목을 입력해주세요")
    String title,

    @NotEmpty(message = "내용을 입력해주세요")
    String content,

    //TODO validate Enum
    @NotEmpty(message = "중요도를 입력해주세요")
    String importance,

    @FutureOrPresent(message = "마감일은 현재 시점 이후여야 합니다.")
    LocalDateTime deadLine
) {

}
