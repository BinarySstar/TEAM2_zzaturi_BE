package goorm.zzaturi.domain.like.service;

import goorm.zzaturi.domain.board.entity.Board;
import goorm.zzaturi.domain.board.repository.BoardRepository;
import goorm.zzaturi.domain.like.dto.LikeRequestDto;
import goorm.zzaturi.domain.like.entity.Like;
import goorm.zzaturi.domain.like.repository.LikeRepository;
import goorm.zzaturi.domain.member.entity.Member;
import goorm.zzaturi.domain.member.repository.MemberRepository;
import goorm.zzaturi.global.exception.AlreadyLikedException;
import goorm.zzaturi.global.exception.BoardNotFoundException;
import goorm.zzaturi.global.exception.LikeNotFoundException;
import goorm.zzaturi.global.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static goorm.zzaturi.global.exception.dto.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public void like(LikeRequestDto requestDto) {
        Member member = memberRepository.findByEmail(requestDto.member().getEmail())
                .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND));

        Board board = boardRepository.findById(requestDto.board().getId())
                .orElseThrow(() -> new BoardNotFoundException(BOARD_NOT_FOUND));

        // 이미 좋아요가 되어 있으면 에러
        if (likeRepository.existsByMemberAndBoard(member, board)) {
            throw new AlreadyLikedException(ALREADY_LIKED);
        }

        Like like = Like.builder()
                .member(member)
                .board(board)
                .build();

        board.setLikeCount(board.getLikeCount() + 1);
        likeRepository.save(like);
    }

    // 좋아요 취소
    public void unlike(LikeRequestDto requestDto) {
        Member member = memberRepository.findByEmail(requestDto.member().getEmail())
                .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND));

        Board board = boardRepository.findById(requestDto.board().getId())
                .orElseThrow(() -> new BoardNotFoundException(BOARD_NOT_FOUND));

        Like like = likeRepository.findByMemberAndBoard(member, board)
                .orElseThrow(() -> new LikeNotFoundException(LIKE_NOT_FOUND));

        board.setLikeCount(board.getLikeCount() - 1);
        likeRepository.delete(like);
    }
}
