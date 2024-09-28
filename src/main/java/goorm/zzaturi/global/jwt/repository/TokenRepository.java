package goorm.zzaturi.global.jwt.repository;

import goorm.zzaturi.domain.member.entity.Member;
import goorm.zzaturi.global.jwt.entity.Token;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByMember(Member member);

    Token findByRefreshToken(String refreshToken);
}