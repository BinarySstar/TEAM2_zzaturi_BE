package goorm.zzaturi.domain.team.controller;

import goorm.zzaturi.domain.team.dto.request.TeamCreateRequestDto;
import goorm.zzaturi.domain.team.dto.request.TeamInviteRequestDto;
import goorm.zzaturi.domain.team.dto.request.TeamLeaveRequestDto;
import goorm.zzaturi.domain.team.dto.request.TeamUpdateRequestDto;
import goorm.zzaturi.domain.team.dto.response.TeamResponseDto;
import goorm.zzaturi.domain.team.entity.Team;
import goorm.zzaturi.domain.team.service.TeamService;
import goorm.zzaturi.global.exception.MemberNotFoundException;
import goorm.zzaturi.global.exception.TeamMemberNotFoundException;
import goorm.zzaturi.global.exception.TeamNotFoundException;
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
@RequestMapping("/api/team")
@Tag(name = "Team Controller", description = "팀 API")
public class TeamController {

    private final TeamService teamService;

    @Operation(summary = "모든 팀 조회", description = "저장된 모든 팀의 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "팀 조회 성공")
    })
    @GetMapping
    public ResponseEntity<List<TeamResponseDto>> getAllTeams() {
        List<Team> teams = teamService.findAll();
        return ResponseEntity.ok(TeamResponseDto.listOf(teams));
    }

    @Operation(summary = "팀 조회", description = "팀의 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "팀 조회 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 팀", content=@Content(schema=@Schema(implementation= TeamNotFoundException.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<TeamResponseDto> getTeam(@PathVariable Long id) {
        Team team = teamService.findById(id);
        return ResponseEntity.ok(TeamResponseDto.of(team));
    }

    @Operation(summary = "팀 생성", description = "팀을 생성합니다.")
    @Parameter(name = "TeamCreateRequestDto", description = "팀 생성 요청 데이터")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "팀 생성 성공")
    })
    @PostMapping
    public ResponseEntity<TeamResponseDto> createTeam(@Valid @RequestBody TeamCreateRequestDto requestDto) {
        Team team = teamService.create(requestDto);
        return ResponseEntity.ok(TeamResponseDto.of(team));
    }

    @Operation(summary = "팀 수정", description = "팀의 정보를 수정합니다.")
    @Parameter(name = "TeamUpdateRequestDto", description = "팀 수정 요청 데이터")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "팀 수정 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 팀", content=@Content(schema=@Schema(implementation=TeamNotFoundException.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<TeamResponseDto> updateTeam(@PathVariable Long id,
                                           @Valid @RequestBody TeamUpdateRequestDto requestDto) {
        Team team = teamService.update(id, requestDto);
        return ResponseEntity.ok(TeamResponseDto.of(team));
    }

    @Operation(summary = "팀 삭제", description = "팀을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "팀 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 팀", content=@Content(schema=@Schema(implementation=TeamNotFoundException.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        teamService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "팀원 초대", description = "팀원을 초대합니다.")
    @Parameter(name = "TeamInviteRequestDto", description = "팀원 초대 요청 데이터")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "팀원 초대 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 팀", content=@Content(schema=@Schema(implementation=TeamNotFoundException.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 회원", content=@Content(schema=@Schema(implementation=MemberNotFoundException.class)))
    })
    @PostMapping("/invite")
    public ResponseEntity<Void> inviteMember(@Valid @RequestBody TeamInviteRequestDto requestDto) {
        teamService.inviteMember(requestDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "팀 탈퇴", description = "팀에서 탈퇴합니다.")
    @Parameter(name = "TeamLeaveRequestDto", description = "팀 탈퇴 요청 데이터")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "팀 탈퇴 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 팀", content=@Content(schema=@Schema(implementation=TeamNotFoundException.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 회원", content=@Content(schema=@Schema(implementation=MemberNotFoundException.class))),
            @ApiResponse(responseCode = "404", description = "팀원이 아님", content=@Content(schema=@Schema(implementation= TeamMemberNotFoundException.class)))
    })
    @DeleteMapping("/leave")
    public ResponseEntity<Void> leaveTeam(@RequestBody TeamLeaveRequestDto requestDto) {
        teamService.leaveTeam(requestDto);
        return ResponseEntity.ok().build();
    }
}
