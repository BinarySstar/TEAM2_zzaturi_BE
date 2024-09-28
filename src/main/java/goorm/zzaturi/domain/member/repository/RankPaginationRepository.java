package goorm.zzaturi.domain.member.repository;

import static goorm.zzaturi.domain.member.entity.QMember.member;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import goorm.zzaturi.domain.member.dto.MemberPaginationDto;
import goorm.zzaturi.domain.member.dto.request.RankPaginationRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class RankPaginationRepository {

    private final JPAQueryFactory queryFactory;
    public static final int RANK_PAGE_SIZE = 10;

    public List<MemberPaginationDto> getRankList(RankPaginationRequest request) {
        BooleanExpression rankCondition = getRankCondition(request.lastMemberId());

        return queryFactory
            .select(Projections.constructor(MemberPaginationDto.class,
                member.nickname,
                member.rank
            ))
            .from(member)
            .where(
                rankCondition,
                member.rank.isNotNull()
            )
            .orderBy(member.rank.asc())
            .limit(RANK_PAGE_SIZE + 1)
            .fetch();
    }

    private BooleanExpression getRankCondition(Long memberId) {
        if (memberId == null) {
            return null;
        }

        return member.id.loe(memberId);
    }
}
