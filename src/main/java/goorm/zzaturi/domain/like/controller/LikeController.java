package goorm.zzaturi.domain.like.controller;

import goorm.zzaturi.domain.like.dto.LikeRequestDto;
import goorm.zzaturi.domain.like.service.LikeService;
import goorm.zzaturi.global.exception.BoardNotFoundException;
import goorm.zzaturi.global.exception.LikeNotFoundException;
import goorm.zzaturi.global.exception.MemberNotFoundException;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/like")
@Tag(name = "Like Controller", description = "좋아요 API")
public class LikeController {

    private final LikeService likeService;

    @Operation(summary = "좋아요", description = "게시글에 좋아요를 누릅니다.")
    @Parameter(name = "LikeRequestDto", description = "좋아요 요청 데이터")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "좋아요 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 회원", content = @Content(schema = @Schema(implementation = MemberNotFoundException.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 게시글", content = @Content(schema = @Schema(implementation = BoardNotFoundException.class))),
            @ApiResponse(responseCode = "409", description = "이미 좋아요한 게시글", content = @Content(schema = @Schema(implementation = MemberNotFoundException.class)))
    })
    @PostMapping
    public ResponseEntity<Void> like(@Valid @RequestBody LikeRequestDto requestDto) {
        likeService.like(requestDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "좋아요 취소", description = "게시글에 눌렀던 좋아요를 취소합니다")
    @Parameter(name = "LikeRequestDto", description = "좋아요 취소 요청 데이터")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "좋아요 취소 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 회원", content = @Content(schema = @Schema(implementation = MemberNotFoundException.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 게시글", content = @Content(schema = @Schema(implementation = BoardNotFoundException.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 좋아요", content = @Content(schema = @Schema(implementation = LikeNotFoundException.class)))
    })
    @DeleteMapping
    public ResponseEntity<Void> unlike(@Valid @RequestBody LikeRequestDto requestDto) {
        likeService.unlike(requestDto);
        return ResponseEntity.ok().build();
    }
}
