package goorm.zzaturi.global.oauth2;


import goorm.zzaturi.domain.member.entity.SocialType;

public interface OAuthInfoResponse {
    String getEmail();
    String getNickname();
    SocialType getOAuthProvider();
}