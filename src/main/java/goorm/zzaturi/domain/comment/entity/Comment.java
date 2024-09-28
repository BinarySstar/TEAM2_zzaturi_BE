package goorm.zzaturi.domain.comment.entity;

import goorm.zzaturi.domain.board.entity.Board;
import goorm.zzaturi.domain.comment.dto.request.CommentUpdateRequestDto;
import goorm.zzaturi.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comment")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;

    @JoinColumn(name = "member_id")
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @JoinColumn(name = "board_id")
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Board board;

    @Column
    private LocalDate createdAt;

    @Column
    private LocalDate modifiedAt;

    @Builder
    public Comment(String content, Member member, Board board, LocalDate createdAt) {
        this.content = content;
        this.member = member;
        this.board = board;
        this.createdAt = createdAt;
    }

    public void update(CommentUpdateRequestDto requestDto) {
        if(requestDto.content() != null)
            this.content = requestDto.content();
        this.modifiedAt = LocalDate.now();
    }
}
