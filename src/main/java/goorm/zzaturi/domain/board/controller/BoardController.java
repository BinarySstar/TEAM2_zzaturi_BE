package goorm.zzaturi.domain.board.controller;

import goorm.zzaturi.domain.board.dto.request.BoardCreateRequestDto;
import goorm.zzaturi.domain.board.dto.request.BoardUpdateRequestDto;
import goorm.zzaturi.domain.board.entity.Board;
import goorm.zzaturi.domain.board.entity.Category;
import goorm.zzaturi.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public ResponseEntity<List<Board>> getBoardList(@RequestParam Category category) {
        List<Board> boardList = boardService.findAllByCategory(category);
        return ResponseEntity.ok(boardList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> get(@PathVariable Long id) {
        Board board = boardService.findById(id);
        return ResponseEntity.ok(board);
    }

    @PostMapping
    public ResponseEntity<Board> create(@RequestBody BoardCreateRequestDto requestDto) {
        Board board = boardService.create(requestDto);
        return ResponseEntity.ok(board);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Board> update(@PathVariable Long id, @RequestBody BoardUpdateRequestDto requestDto) {
        Board board = boardService.update(id, requestDto);
        return ResponseEntity.ok(board);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Board> delete(@PathVariable Long id) {
        Board board = boardService.delete(id);
        return ResponseEntity.ok(board);
    }
}
