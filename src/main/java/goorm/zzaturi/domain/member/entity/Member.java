package goorm.zzaturi.domain.member.entity;

import goorm.zzaturi.global.jwt.entity.Token;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "member")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    private String email;

    private String imageUrl;

    private Long level;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Token token;

    @Builder
    public Member(String nickname, String email, String imageUrl, SocialType socialType,
        Token token) {
        this.nickname = nickname;
        this.email = email;
        this.imageUrl = Objects.equals(imageUrl, "") ? "default url" : imageUrl;
        this.level = 0L;
        this.socialType = socialType;
        this.token = token;
    }
}
