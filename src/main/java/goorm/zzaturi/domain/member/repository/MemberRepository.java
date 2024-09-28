package goorm.zzaturi.domain.member.repository;

import goorm.zzaturi.domain.member.entity.Member;
import goorm.zzaturi.global.exception.member.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MemberRepository {

    private final MemberJpaRepository memberJpaRepository;

    public Member findByEmail(String email) {
        return memberJpaRepository.findByEmail(email)
            .orElseThrow(MemberNotFoundException::new);
    }
}
