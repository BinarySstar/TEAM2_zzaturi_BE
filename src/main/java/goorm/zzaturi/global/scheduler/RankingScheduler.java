package goorm.zzaturi.global.scheduler;

import goorm.zzaturi.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RankingScheduler {

    private final MemberService memberService;

    // 매일 오전 0시에 랭킹을 업데이트
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateMemberRankings() {
        memberService.updateRank();
    }
}
