package goorm.zzaturi.domain.comment.controller;

import goorm.zzaturi.domain.comment.dto.request.CommentCreateRequestDto;
import goorm.zzaturi.domain.comment.dto.request.CommentUpdateRequestDto;
import goorm.zzaturi.domain.comment.dto.response.CommentCreateResponseDto;
import goorm.zzaturi.domain.comment.entity.Comment;
import goorm.zzaturi.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{boardId}")
    public ResponseEntity<List<Comment>> getAllComments(@PathVariable Long boardId) {
        List<Comment> comments = commentService.getAllComments(boardId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/{boardId}")
    public ResponseEntity<CommentCreateResponseDto> createComment(@PathVariable Long boardId,
                                                                  @RequestBody CommentCreateRequestDto requestDto) {
        Comment comment = commentService.createComment(boardId, requestDto);
        return ResponseEntity.ok(CommentCreateResponseDto.of(comment));
    }

    @PutMapping("/{boardId}/{commentId}")
    public ResponseEntity<CommentCreateResponseDto> updateComment(@RequestBody CommentUpdateRequestDto requestDto) {
        Comment comment = commentService.updateComment(requestDto);
        return ResponseEntity.ok(CommentCreateResponseDto.of(comment));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }
}
