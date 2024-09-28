package goorm.zzaturi.domain.board.entity;

import goorm.zzaturi.domain.board.dto.request.BoardUpdateRequestDto;
import goorm.zzaturi.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

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
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate modifiedAt;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Builder
    public Board(String title, String content, Member member, String imageUrl, LocalDate createdAt, Category category) {
        this.title = title;
        this.content = content;
        this.member = member;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.category = category;
    }

    public void update(BoardUpdateRequestDto requestDto) {
        if(requestDto.title() != null)
            this.title = requestDto.title();
        if(requestDto.content() != null)
            this.content = requestDto.content();
        this.imageUrl = requestDto.imageUrl();
        this.modifiedAt = LocalDate.now();
    }
}
