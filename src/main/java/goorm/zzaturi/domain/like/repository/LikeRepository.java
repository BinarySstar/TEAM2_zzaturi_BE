package goorm.zzaturi.domain.like.repository;

import goorm.zzaturi.domain.board.entity.Board;
import goorm.zzaturi.domain.like.entity.Like;
import goorm.zzaturi.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByMemberAndBoard(Member member, Board board);

    Optional<Like> findByMemberAndBoard(Member member, Board board);
}
