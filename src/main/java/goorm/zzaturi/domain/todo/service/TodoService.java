package goorm.zzaturi.domain.todo.service;

import static goorm.zzaturi.domain.todo.repository.TodoPaginationRepository.PAGE_SIZE;

import goorm.zzaturi.domain.member.entity.Member;
import goorm.zzaturi.domain.member.repository.MemberRepository;
import goorm.zzaturi.domain.todo.dto.TodoPaginationResponseWrapper;
import goorm.zzaturi.domain.todo.dto.request.TodoCreateRequest;
import goorm.zzaturi.domain.todo.dto.request.TodoPaginationRequest;
import goorm.zzaturi.domain.todo.dto.response.TodoPaginationDto;
import goorm.zzaturi.domain.todo.entity.Importance;
import goorm.zzaturi.domain.todo.entity.Todo;
import goorm.zzaturi.domain.todo.repository.TodoPaginationRepository;
import goorm.zzaturi.domain.todo.repository.TodoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final TodoPaginationRepository todoPaginationRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long create(TodoCreateRequest request, String email) {
        Member member = memberRepository.findByEmail(email);

        Todo todo = Todo.create()
            .title(request.title())
            .deadLine(request.deadLine())
            .content(request.content())
            .importance(Importance.valueOf(request.importance()))
            .member(member)
            .build();

        return todoRepository.save(todo);
    }

    @Transactional(readOnly = true)
    public TodoPaginationResponseWrapper getPaginatedTodos(TodoPaginationRequest request,
        String email) {
        Member member = memberRepository.findByEmail(email);

        //TODO team 추가 시 팀 가져오는 로직 추가
        List<TodoPaginationDto> paginatedTodos = todoPaginationRepository.getTodoList(member,
            request);

        boolean hasNext = determineHasNextPage(paginatedTodos);

        return TodoPaginationResponseWrapper.builder()
            .todos(paginatedTodos)
            .hasNext(hasNext)
            .build();
    }

    private boolean determineHasNextPage(List<TodoPaginationDto> paginatedTodos) {
        if (paginatedTodos.size() > PAGE_SIZE) {
            paginatedTodos.remove(PAGE_SIZE);
            return true;
        }
        return false;
    }
}
