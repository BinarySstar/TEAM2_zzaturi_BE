package goorm.zzaturi.domain.todo.repository;

import goorm.zzaturi.domain.todo.entity.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class TodoRepository {

    private final TodoJpaRepository todoJpaRepository;

    public Long save(Todo todo) {
        return todoJpaRepository.save(todo).getId();
    }
}
