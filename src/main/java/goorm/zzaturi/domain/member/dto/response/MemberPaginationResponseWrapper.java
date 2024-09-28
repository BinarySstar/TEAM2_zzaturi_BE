package goorm.zzaturi.domain.member.dto.response;

import goorm.zzaturi.domain.member.dto.MemberPaginationDto;
import java.util.List;
import lombok.Builder;

@Builder
public record MemberPaginationResponseWrapper(

    List<MemberPaginationDto> members,

    boolean hasNext
) {

}
