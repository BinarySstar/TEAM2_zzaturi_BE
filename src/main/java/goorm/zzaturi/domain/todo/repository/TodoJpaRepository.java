package goorm.zzaturi.domain.todo.repository;

import goorm.zzaturi.domain.member.entity.Member;
import goorm.zzaturi.domain.todo.entity.Todo;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TodoJpaRepository extends JpaRepository<Todo, Long> {

    Optional<Todo> findByIdAndMember(Long todoId, Member member);

    @Query("SELECT t.member, COUNT(t) AS completedCount " +
        "FROM Todo t " +
        "WHERE t.completed = true " +
        "AND t.completedAt BETWEEN :startOfWeek AND :endOfWeek " +
        "GROUP BY t.member " +
        "ORDER BY completedCount DESC")
    List<Object[]> findCompletedTodoCountForAllMembers(@Param("startOfWeek") LocalDateTime startOfWeek, @Param("endOfWeek") LocalDateTime endOfWeek);
}
