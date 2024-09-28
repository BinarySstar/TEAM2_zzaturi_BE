package goorm.zzaturi.domain.todo.repository;

import goorm.zzaturi.domain.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoJpaRepository extends JpaRepository<Todo, Long> {

}
