package goorm.zzaturi.domain.todo.entity;

import goorm.zzaturi.domain.member.entity.Member;
import goorm.zzaturi.domain.todo.dto.request.TodoUpdateRequest;
import goorm.zzaturi.global.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "todos")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Todo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private boolean completed;

    private Importance importance;

    private LocalDateTime deadLine;

    private LocalDateTime completedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    private Todo(String title, String content, Importance importance,
        LocalDateTime deadLine, Member member) {
        this.title = title;
        this.content = content;
        this.completed = false;
        this.importance = importance;
        this.deadLine = deadLine;
        this.member = member;
    }

    public static Todo.TodoBuilder create() {
        return Todo.builder();
    }

    public void update(TodoUpdateRequest request) {
        this.title = request.title();
        this.content = request.content();
        this.importance = Importance.valueOf(request.importance());
        this.deadLine = request.deadLine();
    }

    public void todoComplete() {
        this.completed = !this.completed;
        if (this.completed) {
            this.completedAt = LocalDateTime.now();
        } else {
            this.completedAt = null;
        }
    }

}