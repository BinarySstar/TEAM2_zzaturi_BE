package goorm.zzaturi.global.oauth2;


import goorm.zzaturi.domain.member.entity.SocialType;

public interface OAuthApiClient {
    SocialType oAuthProvider();
    String requestAccessToken(OAuthLoginParams params);
    OAuthInfoResponse requestOauthInfo(String accessToken);
}