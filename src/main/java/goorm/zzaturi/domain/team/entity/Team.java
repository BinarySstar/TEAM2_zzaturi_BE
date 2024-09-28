package goorm.zzaturi.domain.team.entity;

import goorm.zzaturi.domain.member.entity.Member;
import goorm.zzaturi.domain.team.dto.request.TeamUpdateRequestDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "team")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String inviteCode;

    @JoinColumn(name = "leader_id")
    @OneToOne
    private Member leader;

    @Column(columnDefinition = "boolean default true")
    private Boolean isPublic;

    @Builder
    public Team(String name, String inviteCode, Member leader, boolean isPublic) {
        this.name = name;
        this.inviteCode = inviteCode;
        this.leader = leader;
        this.isPublic = isPublic;
    }

    public void update(TeamUpdateRequestDto requestDto) {
        if(requestDto.name() != null)
            this.name = requestDto.name();
        if(requestDto.isPublic() != null)
            this.isPublic = requestDto.isPublic();
    }
}
