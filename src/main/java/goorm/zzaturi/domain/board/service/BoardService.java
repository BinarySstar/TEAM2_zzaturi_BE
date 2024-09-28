package goorm.zzaturi.domain.board.service;

import goorm.zzaturi.domain.board.dto.request.BoardCreateRequestDto;
import goorm.zzaturi.domain.board.dto.request.BoardUpdateRequestDto;
import goorm.zzaturi.domain.board.entity.Board;
import goorm.zzaturi.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Board> showALl() {
        return boardRepository.findAll();
    }

    @Transactional
    public Optional<Board> show(Long id) {
        return boardRepository.findById(id);
    }

    @Transactional
    public Board create(BoardCreateRequestDto requestDto) {
        Board board = Board.builder()
                .title(requestDto.title())
                .content(requestDto.content())
                .member(requestDto.member())
                .imageUrl(requestDto.imageUrl())
                .category(requestDto.category())
                .createdAt(LocalDate.now())
                .build();
        return boardRepository.save(board);
    }

    @Transactional
    public Board update(BoardUpdateRequestDto requestDto) {
        Board board = boardRepository.findById(requestDto.id())
                .orElseThrow(() -> new RuntimeException("해당 게시글이 없습니다."));
        board.update(requestDto);
        return boardRepository.save(board);
    }

    @Transactional
    public Board delete(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 게시글이 없습니다."));
        boardRepository.delete(board);
        return board;
    }

}
