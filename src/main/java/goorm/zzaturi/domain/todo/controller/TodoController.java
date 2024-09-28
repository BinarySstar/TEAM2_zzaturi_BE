package goorm.zzaturi.domain.todo.controller;

import goorm.zzaturi.domain.todo.dto.TodoPaginationResponseWrapper;
import goorm.zzaturi.domain.todo.dto.request.TodoCreateRequest;
import goorm.zzaturi.domain.todo.dto.request.TodoPaginationRequest;
import goorm.zzaturi.domain.todo.dto.request.TodoUpdateRequest;
import goorm.zzaturi.domain.todo.service.TodoService;
import goorm.zzaturi.global.exception.dto.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/todos")
@RestController
@Tag(name = "Todo Controller", description = "Todo API")
public class TodoController {

    private final TodoService todoService;

    @Operation(summary = "투두 리스트 생성", description = "투두리스트를 생성합니다.")
    @PostMapping
    public ResponseEntity<Long> create(@Valid @RequestBody TodoCreateRequest request,
        @AuthenticationPrincipal String email) {
        Long savedTodoId = todoService.create(request, email);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedTodoId);
    }

    @Operation(summary = "투두 리스트 페이징 조회", description = "페이징된 투두 목록 출력입니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "페이징 성공", content = @Content(schema = @Schema(implementation = TodoPaginationResponseWrapper.class))),
        @ApiResponse(responseCode = "400", description = "페이징 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping
    public ResponseEntity<TodoPaginationResponseWrapper> getPaginatedTodos(
        @RequestBody @Valid TodoPaginationRequest request,
        @AuthenticationPrincipal String email) {
        TodoPaginationResponseWrapper paginatedTodo = todoService.getPaginatedTodos(request,
            email);

        return ResponseEntity.status(HttpStatus.OK).body(paginatedTodo);
    }

    @Operation(summary = "투두리스트 수정", description = "투두리스트를 수정 합니다")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "수정 성공"),
        @ApiResponse(responseCode = "400", description = "수정 실패"),
    })
    @PatchMapping("/{todoId}")
    public ResponseEntity<Void> update(@Valid @RequestBody TodoUpdateRequest request,
        @AuthenticationPrincipal String email,
        @PathVariable Long todoId) {
        todoService.update(request, email, todoId);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "투두리스트 완료", description = "투두리스트를 완료 합니다")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "완료 성공"),
        @ApiResponse(responseCode = "400", description = "완료 실패"),
    })
    @PatchMapping("/complete/{todoId}")
    public ResponseEntity<Void> complete(@AuthenticationPrincipal String email,
        @PathVariable Long todoId) {
        todoService.complete(todoId, email);

        return ResponseEntity.noContent().build();
    }
}
