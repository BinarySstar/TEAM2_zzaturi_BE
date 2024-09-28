package goorm.zzaturi.domain.team.service;

import goorm.zzaturi.domain.member.entity.Member;
import goorm.zzaturi.domain.member.repository.MemberRepository;
import goorm.zzaturi.domain.team.dto.request.TeamCreateRequestDto;
import goorm.zzaturi.domain.team.dto.request.TeamInviteRequestDto;
import goorm.zzaturi.domain.team.dto.request.TeamLeaveRequestDto;
import goorm.zzaturi.domain.team.dto.request.TeamUpdateRequestDto;
import goorm.zzaturi.domain.team.entity.Team;
import goorm.zzaturi.domain.team.entity.TeamMember;
import goorm.zzaturi.domain.team.repository.TeamMemberRepository;
import goorm.zzaturi.domain.team.repository.TeamRepository;
import goorm.zzaturi.global.exception.MemberNotFoundException;
import goorm.zzaturi.global.exception.TeamMemberNotFoundException;
import goorm.zzaturi.global.exception.TeamNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static goorm.zzaturi.global.exception.dto.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;
    private final TeamMemberRepository teamMemberRepository;

    @Transactional
    public Team create(TeamCreateRequestDto requestDto) {
        Team team = Team.builder()
                .name(requestDto.name())
                .inviteCode(String.valueOf(UUID.randomUUID()))
                .leader(requestDto.leader())
                .isPublic(requestDto.isPublic())
                .build();
        return teamRepository.save(team);
    }

    @Transactional(readOnly = true)
    public Team findById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException(TEAM_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    @Transactional
    public Team update(Long id, TeamUpdateRequestDto requestDto) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException(TEAM_NOT_FOUND));
        team.update(requestDto);
        return team;
    }

    @Transactional
    public void delete(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException(TEAM_NOT_FOUND));
        teamRepository.delete(team);
    }

    @Transactional
    public void inviteMember(TeamInviteRequestDto requestDto) {
        Team team = teamRepository.findByInviteCode(requestDto.inviteCode())
                .orElseThrow(() -> new TeamNotFoundException(TEAM_NOT_FOUND));
        Member member = memberRepository.findByEmail(requestDto.email())
                .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND));

        TeamMember teamMember = TeamMember.builder()
                .team(team)
                .member(member)
                .build();

        teamMemberRepository.save(teamMember);
    }

    @Transactional
    public void leaveTeam(TeamLeaveRequestDto requestDto) {
        Team team = teamRepository.findByName(requestDto.teamName())
                .orElseThrow(() -> new TeamNotFoundException(TEAM_NOT_FOUND));
        Member member = memberRepository.findByEmail(requestDto.email())
                .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND));

        TeamMember teamMember = teamMemberRepository.findByTeamAndMember(team, member)
                .orElseThrow(() -> new TeamMemberNotFoundException(TEAM_MEMBER_NOT_FOUND));
        teamMemberRepository.delete(teamMember);
    }
}
