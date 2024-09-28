package goorm.zzaturi.domain.member.service;

import static goorm.zzaturi.domain.member.repository.RankPaginationRepository.RANK_PAGE_SIZE;

import goorm.zzaturi.domain.member.dto.MemberPaginationDto;
import goorm.zzaturi.domain.member.dto.MemberRankingDto;
import goorm.zzaturi.domain.member.dto.request.RankPaginationRequest;
import goorm.zzaturi.domain.member.dto.response.MemberDetailResponse;
import goorm.zzaturi.domain.member.dto.response.MemberPaginationResponseWrapper;
import goorm.zzaturi.domain.member.entity.Member;
import goorm.zzaturi.domain.member.repository.MemberRepository;
import goorm.zzaturi.domain.member.repository.RankPaginationRepository;
import goorm.zzaturi.domain.todo.repository.TodoRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final TodoRepository todoRepository;
    private final RankPaginationRepository rankPaginationRepository;

    @Transactional(readOnly = true)
    public MemberDetailResponse getMemberDetail(String email) {
        Member member = memberRepository.findByEmail(email);

        return new MemberDetailResponse(member.getId(), member.getNickname(), member.getEmail(),
            member.getLevel(), member.getRank());
    }

    @Transactional
    public void updateRank() {
        List<MemberRankingDto> rankings = getMemberRankingsForThisWeek();
        for (MemberRankingDto ranking : rankings) {
            ranking.member().updateRank(ranking.completedTodoCount());
        }
    }

    @Transactional(readOnly = true)
    public MemberPaginationResponseWrapper getPaginatedMembers(RankPaginationRequest request) {
        List<MemberPaginationDto> rankList = rankPaginationRepository.getRankList(request);
        boolean hasNext = determineHasNextPage(rankList);

        return MemberPaginationResponseWrapper.builder()
            .members(rankList)
            .hasNext(hasNext)
            .build();
    }

    private List<MemberRankingDto> getMemberRankingsForThisWeek() {
        List<Object[]> results = todoRepository.findCompletedTodoCountForAllMembers();

        return results.stream()
            .map(result -> new MemberRankingDto((Member) result[0], (Long) result[1]))
            .collect(Collectors.toList());
    }

    private boolean determineHasNextPage(List<MemberPaginationDto> paginatedMembers) {
        if (paginatedMembers.size() > RANK_PAGE_SIZE) {
            paginatedMembers.remove(RANK_PAGE_SIZE);
            return true;
        }
        return false;
    }
}
