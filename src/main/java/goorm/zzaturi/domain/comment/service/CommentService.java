package goorm.zzaturi.domain.comment.service;

import goorm.zzaturi.domain.board.entity.Board;
import goorm.zzaturi.domain.board.repository.BoardRepository;
import goorm.zzaturi.domain.comment.dto.request.CommentCreateRequestDto;
import goorm.zzaturi.domain.comment.dto.request.CommentUpdateRequestDto;
import goorm.zzaturi.domain.comment.entity.Comment;
import goorm.zzaturi.domain.comment.repository.CommentRepository;
import goorm.zzaturi.global.exception.BoardNotFoundException;
import goorm.zzaturi.global.exception.CommentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static goorm.zzaturi.global.exception.dto.ErrorCode.BOARD_NOT_FOUND;
import static goorm.zzaturi.global.exception.dto.ErrorCode.COMMENT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public Comment createComment(Long boardId, CommentCreateRequestDto requestDto) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new BoardNotFoundException(BOARD_NOT_FOUND));

        Comment comment = Comment.builder()
                .content(requestDto.content())
                .member(requestDto.member())
                .board(board)
                .createdAt(LocalDate.now())
                .build();

        return commentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    public List<Comment> getAllComments(Long boardId) {
        return commentRepository.findAllByBoardId(boardId);
    }

    @Transactional
    public Comment updateComment(Long commentId, CommentUpdateRequestDto requestDto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(COMMENT_NOT_FOUND));

        comment.update(requestDto);
        return commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(COMMENT_NOT_FOUND));

        commentRepository.delete(comment);
    }

}
