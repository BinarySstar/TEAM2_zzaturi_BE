package goorm.zzaturi.domain.todo.repository;

import goorm.zzaturi.domain.member.entity.Member;
import goorm.zzaturi.domain.todo.entity.Todo;
import goorm.zzaturi.global.exception.todo.TodoNotFoundException;
import goorm.zzaturi.global.util.DateUtils;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class TodoRepository {

    private final TodoJpaRepository todoJpaRepository;

    public Long save(Todo todo) {
        return todoJpaRepository.save(todo).getId();
    }

    public Todo findById(Long todoId) {
        return todoJpaRepository.findById(todoId)
            .orElseThrow(TodoNotFoundException::new);
    }

    public Todo findByIdAndMember(Long todoId, Member member) {
        return todoJpaRepository.findByIdAndMember(todoId, member)
            .orElseThrow(TodoNotFoundException::new);
    }

    public List<Object[]> findCompletedTodoCountForAllMembers() {
        LocalDateTime startOfWeek = DateUtils.getStartOfWeek();
        LocalDateTime endOfWeek = DateUtils.getEndOfWeek();

        return todoJpaRepository.findCompletedTodoCountForAllMembers(startOfWeek, endOfWeek);
    }
}
