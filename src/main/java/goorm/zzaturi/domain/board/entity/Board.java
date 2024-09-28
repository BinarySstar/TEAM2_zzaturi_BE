package goorm.zzaturi.domain.board.entity;

import goorm.zzaturi.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "board")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    @ManyToOne
    private Member member;

    @Column
    private String imageUrl;

    @Column(nullable = false)
    // @CreatedDate
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate modifiedAt;

    // @Column(columnDefinition = "integer default 0", nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;
}
