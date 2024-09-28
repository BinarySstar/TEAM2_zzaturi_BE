package goorm.zzaturi.global.oauth2.kakao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import goorm.zzaturi.domain.member.entity.SocialType;
import goorm.zzaturi.global.oauth2.OAuthInfoResponse;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoInfoResponse implements OAuthInfoResponse {

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class KakaoAccount {
        private KakaoProfile profile;
        private String email;
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class KakaoProfile {
        private String nickname;
    }

    @Override
    public String getEmail() {
        return kakaoAccount.email;
    }

    @Override
    public String getNickname() {
        return kakaoAccount.profile.nickname;
    }

    @Override
    public SocialType getOAuthProvider() {
        return SocialType.KAKAO;
    }
}