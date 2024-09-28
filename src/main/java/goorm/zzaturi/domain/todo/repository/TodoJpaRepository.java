package goorm.zzaturi.domain.todo.repository;

import goorm.zzaturi.domain.member.entity.Member;
import goorm.zzaturi.domain.todo.entity.Todo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoJpaRepository extends JpaRepository<Todo, Long> {

    Optional<Todo> findByIdAndMember(Long todoId, Member member);
}
