package goorm.zzaturi.domain.comment.controller;

import goorm.zzaturi.domain.comment.dto.request.CommentCreateRequestDto;
import goorm.zzaturi.domain.comment.dto.request.CommentUpdateRequestDto;
import goorm.zzaturi.domain.comment.dto.response.CommentResponseDto;
import goorm.zzaturi.domain.comment.entity.Comment;
import goorm.zzaturi.domain.comment.service.CommentService;
import goorm.zzaturi.global.exception.BoardNotFoundException;
import goorm.zzaturi.global.exception.CommentNotFoundException;
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
@RequestMapping("/api/comment")
@Tag(name = "Comment Controller", description = "댓글 API")
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 목록 조회", description = "게시판에 포함되어 있는 모든 댓글을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "댓글 목록 조회 성공", content=@Content(schema=@Schema(implementation=Comment.class)))
    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> getAllComments(@RequestParam Long boardId) {
        List<Comment> comments = commentService.getAllComments(boardId);
        return ResponseEntity.ok(CommentResponseDto.listOf(comments));
    }

    @Operation(summary = "댓글 작성", description = "게시판에 댓글을 작성합니다.")
    @Parameter(name = "CommentCreateRequestDto", description = "댓글 작성 요청 데이터")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "댓글 작성 성공", content = @Content(schema = @Schema(implementation = CommentResponseDto.class))),
        @ApiResponse(responseCode = "404", description = "존재하지 않는 게시판", content = @Content(schema = @Schema(implementation = BoardNotFoundException.class)))
    })
    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@RequestParam Long boardId,
                                                            @Valid @RequestBody CommentCreateRequestDto requestDto) {
        Comment comment = commentService.createComment(boardId, requestDto);
        return ResponseEntity.ok(CommentResponseDto.of(comment));
    }

    @Operation(summary = "댓글 수정", description = "게시판에 작성된 댓글을 수정합니다.")
    @Parameter(name = "CommentUpdateRequestDto", description = "댓글 수정 요청 데이터")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "댓글 수정 성공", content = @Content(schema = @Schema(implementation = CommentResponseDto.class))),
        @ApiResponse(responseCode = "404", description = "존재하지 않는 댓글", content = @Content(schema = @Schema(implementation = CommentNotFoundException.class)))
    })
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long commentId,
                                                            @Valid @RequestBody CommentUpdateRequestDto requestDto) {
        Comment comment = commentService.updateComment(commentId, requestDto);
        return ResponseEntity.ok(CommentResponseDto.of(comment));
    }

    @Operation(summary = "댓글 삭제", description = "게시판에 작성된 댓글을 삭제합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "댓글 삭제 성공"),
        @ApiResponse(responseCode = "404", description = "존재하지 않는 댓글", content = @Content(schema = @Schema(implementation = CommentNotFoundException.class)))
    })
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }
}
