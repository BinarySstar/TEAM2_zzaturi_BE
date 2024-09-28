package goorm.zzaturi.domain.board.repository;

import goorm.zzaturi.domain.board.entity.Board;
import goorm.zzaturi.domain.board.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAllByCategory(Category category);
}
