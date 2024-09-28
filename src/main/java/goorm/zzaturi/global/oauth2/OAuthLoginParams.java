package goorm.zzaturi.global.oauth2;

import goorm.zzaturi.domain.member.entity.SocialType;
import org.springframework.util.MultiValueMap;


public interface OAuthLoginParams {
    SocialType oAuthProvider();
    MultiValueMap<String, String> makeBody();
}