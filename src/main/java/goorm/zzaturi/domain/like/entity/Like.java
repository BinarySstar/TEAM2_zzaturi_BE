package goorm.zzaturi.domain.like.entity;

import goorm.zzaturi.domain.board.entity.Board;
import goorm.zzaturi.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "like")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn
    @ManyToOne
    private Member member;

    @JoinColumn
    @ManyToOne
    private Board board;

    @Column
    private LocalDate createdAt;

    @Builder
    public Like(Member member, Board board, LocalDate createdAt) {
        this.member = member;
        this.board = board;
        this.createdAt = createdAt;
    }
}
