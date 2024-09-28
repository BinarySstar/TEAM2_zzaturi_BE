package goorm.zzaturi.domain.todo.controller;

import goorm.zzaturi.domain.todo.dto.TodoPaginationResponseWrapper;
import goorm.zzaturi.domain.todo.dto.request.TodoCreateRequest;
import goorm.zzaturi.domain.todo.dto.request.TodoPaginationRequest;
import goorm.zzaturi.domain.todo.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/todos")
@RestController
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<Long> create(@Valid @RequestBody TodoCreateRequest request,
        @AuthenticationPrincipal String email) {
        Long savedTodoId = todoService.create(request, email);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedTodoId);
    }

    @GetMapping
    public ResponseEntity<TodoPaginationResponseWrapper> getPaginatedTodos(
        @RequestBody @Valid TodoPaginationRequest request,
        @AuthenticationPrincipal String email) {
        TodoPaginationResponseWrapper paginatedTodo = todoService.getPaginatedTodos(request,
            email);

        return ResponseEntity.status(HttpStatus.OK).body(paginatedTodo);
    }
}
