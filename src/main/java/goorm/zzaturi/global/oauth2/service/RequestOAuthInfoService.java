package goorm.zzaturi.global.oauth2.service;

import goorm.zzaturi.domain.member.entity.SocialType;
import goorm.zzaturi.global.oauth2.OAuthApiClient;
import goorm.zzaturi.global.oauth2.OAuthInfoResponse;
import goorm.zzaturi.global.oauth2.OAuthLoginParams;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

@Component
public class RequestOAuthInfoService {

    private final Map<SocialType, OAuthApiClient> clients;

    public RequestOAuthInfoService(List<OAuthApiClient> clients) {
        this.clients = clients.stream().collect(
            Collectors.toUnmodifiableMap(OAuthApiClient::oAuthProvider, Function.identity())
        );
    }

    public OAuthInfoResponse request(OAuthLoginParams params) throws Exception {
        try {
            OAuthApiClient client = clients.get(params.oAuthProvider());
            String accessToken = client.requestAccessToken(params);
            return client.requestOauthInfo(accessToken);
        } catch (RestClientException e) {
//            throw new OAuthException(ErrorCode.OAUTH_BAD_REQUEST);
            throw new Exception(e.getMessage());
        }
    }
}