package goorm.zzaturi.global.oauth2.kakao;

import goorm.zzaturi.domain.member.entity.SocialType;
import goorm.zzaturi.global.oauth2.OAuthLoginParams;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Setter
@Getter
@NoArgsConstructor
public class KakaoLoginParams implements OAuthLoginParams {

    private String authorizationCode;

    @Override
    public SocialType oAuthProvider() {
        return SocialType.KAKAO;
    }

    @Override
    public MultiValueMap<String, String> makeBody() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("code", authorizationCode);
        return body;
    }
}
