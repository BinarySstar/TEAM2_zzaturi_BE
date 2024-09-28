package goorm.zzaturi.domain.member.dto;

import goorm.zzaturi.domain.member.entity.Member;

public record MemberRankingDto(Member member, Long completedTodoCount) {

}
