package goorm.zzaturi.domain.board.dto.response;

import goorm.zzaturi.domain.board.entity.Board;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.util.List;

@Builder
public record BoardResponseDto(
        @NotNull(message = "제목이 있어야 합니다.") String title,
        @NotNull(message = "내용이 있어야 합니다.") String content,
        String imageUrl,
        @NotNull(message = "작성자가 있어야 합니다.") String memberNickname,
        @NotNull(message = "카테고리가 있어야 합니다.") String category,
        @Positive(message = "좋아요 수는 0보다 커야 합니다.") int likeCount)
{

    public static BoardResponseDto of(Board board) {
        return BoardResponseDto.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .imageUrl(board.getImageUrl())
                .memberNickname(board.getMember().getNickname())
                .category(board.getCategory().getName())
                .likeCount(board.getLikeCount())
                .build();
    }

    public static List<BoardResponseDto> ofList(List<Board> boardList) {
        return boardList.stream()
                .map(BoardResponseDto::of)
                .toList();
    }
}
