package goorm.zzaturi.domain.board.controller;

import goorm.zzaturi.domain.board.dto.request.BoardCreateRequestDto;
import goorm.zzaturi.domain.board.dto.request.BoardUpdateRequestDto;
import goorm.zzaturi.domain.board.dto.response.BoardResponseDto;
import goorm.zzaturi.domain.board.entity.Board;
import goorm.zzaturi.domain.board.entity.Category;
import goorm.zzaturi.domain.board.service.BoardService;
import goorm.zzaturi.global.exception.BoardNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
@Tag(name = "Board Controller", description = "게시판 API")
public class BoardController {

    private final BoardService boardService;

    @Operation(summary = "게시판 목록 조회", description = "카테고리 별로 게시판 목록을 조회합니다.")
    @Parameter(name = "category", description = "카테고리")
    @ApiResponse(responseCode = "200", description = "게시판 목록 조회 성공", content=@Content(schema=@Schema(implementation=Board.class)))
    @GetMapping("/list")
    public ResponseEntity<List<BoardResponseDto>> getBoardList(@RequestParam Category category) {
        List<Board> boardList = boardService.findAllByCategory(category);
        return ResponseEntity.ok(BoardResponseDto.ofList(boardList));
    }

    @Operation(summary = "게시글 상세 조회", description = "게시글을 상세하게 조회합니다.")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "게시글 상세 조회 성공", content = @Content(schema = @Schema(implementation = Board.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 게시글", content = @Content(schema = @Schema(implementation = BoardNotFoundException.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> get(@PathVariable Long id) {
        Board board = boardService.findById(id);
        return ResponseEntity.ok(BoardResponseDto.of(board));
    }

    @Operation(summary = "게시글 작성", description = "게시글을 작성합니다.")
    @Parameter(name = "BoardCreateRequestDto", description = "게시글 작성 요청 데이터")
    @ApiResponse(responseCode = "200", description = "게시글 작성 성공", content=@Content(schema=@Schema(implementation=Board.class)))
    @PostMapping
    public ResponseEntity<BoardResponseDto> create(@Valid @RequestBody BoardCreateRequestDto requestDto) {
        Board board = boardService.create(requestDto);
        return ResponseEntity.ok(BoardResponseDto.of(board));
    }

    @Operation(summary = "게시글 수정", description = "게시글을 수정합니다.")
    @Parameter(name = "BoardUpdateRequestDto", description = "게시글 수정 요청 데이터")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "게시글 수정 성공", content = @Content(schema = @Schema(implementation = Board.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 게시글", content = @Content(schema = @Schema(implementation = BoardNotFoundException.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<BoardResponseDto> update(@PathVariable Long id, @Valid @RequestBody BoardUpdateRequestDto requestDto) {
        Board board = boardService.update(id, requestDto);
        return ResponseEntity.ok(BoardResponseDto.of(board));
    }

    @Operation(summary = "게시글 삭제", description = "게시글을 삭제합니다.")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "게시글 삭제 성공", content = @Content(schema = @Schema(implementation = Board.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 게시글", content = @Content(schema = @Schema(implementation = BoardNotFoundException.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Board board = boardService.delete(id);
        return ResponseEntity.ok().build();
    }
}
