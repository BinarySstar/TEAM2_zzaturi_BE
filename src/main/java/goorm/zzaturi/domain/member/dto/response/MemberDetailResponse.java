package goorm.zzaturi.domain.member.dto.response;

public record MemberDetailResponse(

    Long id,

    String nickName,

    String email,

    Long level,

    Long rank
) {

}
