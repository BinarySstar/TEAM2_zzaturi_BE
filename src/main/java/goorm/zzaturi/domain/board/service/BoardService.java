package goorm.zzaturi.domain.board.service;

import goorm.zzaturi.domain.board.dto.request.BoardCreateRequestDto;
import goorm.zzaturi.domain.board.dto.request.BoardUpdateRequestDto;
import goorm.zzaturi.domain.board.entity.Board;
import goorm.zzaturi.domain.board.entity.Category;
import goorm.zzaturi.domain.board.repository.BoardRepository;
import goorm.zzaturi.global.exception.BoardNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static goorm.zzaturi.global.exception.dto.ErrorCode.BOARD_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Board> findAllByCategory(Category category) {
        return boardRepository.findAllByCategory(category);
    }

    @Transactional
    public Board findById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new BoardNotFoundException(BOARD_NOT_FOUND));
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
    public Board update(Long id, BoardUpdateRequestDto requestDto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new BoardNotFoundException(BOARD_NOT_FOUND));
        board.update(requestDto);
        return boardRepository.save(board);
    }

    @Transactional
    public Board delete(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new BoardNotFoundException(BOARD_NOT_FOUND));
        boardRepository.delete(board);
        return board;
    }

}
