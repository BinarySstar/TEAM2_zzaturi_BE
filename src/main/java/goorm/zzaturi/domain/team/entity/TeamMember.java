package goorm.zzaturi.domain.team.entity;

import goorm.zzaturi.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "teamMember")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "team_id")
    @ManyToOne
    private Team team;

    @JoinColumn(name = "member_id")
    @ManyToOne
    private Member member;

    @Builder
    public TeamMember(Team team, Member member) {
        this.team = team;
        this.member = member;
    }
}
