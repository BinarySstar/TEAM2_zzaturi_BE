package goorm.zzaturi.domain.member.controller;

import goorm.zzaturi.domain.member.dto.request.RankPaginationRequest;
import goorm.zzaturi.domain.member.dto.response.MemberDetailResponse;
import goorm.zzaturi.domain.member.dto.response.MemberPaginationResponseWrapper;
import goorm.zzaturi.domain.member.service.MemberService;
import goorm.zzaturi.global.exception.dto.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/member")
@RequiredArgsConstructor
@RestController
@Tag(name = "Member Controller", description = "Member API")
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "유저 정보 확인", description = "유저의 정보를 리턴합니다.")
    @GetMapping
    public ResponseEntity<MemberDetailResponse> getMemberDetail(
        @AuthenticationPrincipal String email) {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.getMemberDetail(email));
    }

    @Operation(summary = "랭킹 페이징 조회", description = "페이징된 랭킹 목록 출력입니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "페이징 성공", content = @Content(schema = @Schema(implementation = MemberPaginationResponseWrapper.class))),
        @ApiResponse(responseCode = "400", description = "페이징 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping("/list")
    public ResponseEntity<MemberPaginationResponseWrapper> getMemberPagination(
        @RequestBody @Valid RankPaginationRequest request,
        @AuthenticationPrincipal String email) {
        MemberPaginationResponseWrapper responseWrapper = memberService.getPaginatedMembers(
            request);

        return ResponseEntity.status(HttpStatus.OK).body(responseWrapper);
    }
}
