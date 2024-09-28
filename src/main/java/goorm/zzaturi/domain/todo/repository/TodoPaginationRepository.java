package goorm.zzaturi.domain.todo.repository;

import static goorm.zzaturi.domain.todo.entity.QTodo.todo;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import goorm.zzaturi.domain.member.entity.Member;
import goorm.zzaturi.domain.todo.dto.request.TodoPaginationRequest;
import goorm.zzaturi.domain.todo.dto.response.TodoPaginationDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@RequiredArgsConstructor
@Repository
public class TodoPaginationRepository {

    private final JPAQueryFactory queryFactory;
    public static final int PAGE_SIZE = 10;

    public List<TodoPaginationDto> getTodoList(Member member, TodoPaginationRequest request) {
        BooleanExpression condition = getTodoCondition(request.lastTodoId());

        return queryFactory
            .select(Projections.constructor(TodoPaginationDto.class,
                todo.id.as("todoId"),
                todo.title,
                todo.content,
                todo.completed,
                todo.importance.stringValue(),
                todo.deadLine,
                todo.completedAt,
                todo.createTime.as("createdAt")
            ))
            .from(todo)
            .where(
                todo.member.id.eq(member.getId()),
                condition
            )
            .orderBy(todo.importance.asc())
            .limit(PAGE_SIZE + 1)
            .fetch();
    }

    private BooleanExpression getTodoCondition(Long todoId) {
        if (todoId == null) {
            return null;
        }

        return todo.id.loe(todoId);
    }

}
