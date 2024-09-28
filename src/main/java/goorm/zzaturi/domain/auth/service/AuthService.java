package goorm.zzaturi.domain.auth.service;

import goorm.zzaturi.domain.auth.dto.request.ReIssueRequest;
import goorm.zzaturi.domain.member.entity.Member;
import goorm.zzaturi.domain.member.repository.MemberJpaRepository;
import goorm.zzaturi.global.jwt.dto.TokenDto;
import goorm.zzaturi.global.jwt.entity.Token;
import goorm.zzaturi.global.jwt.provider.JwtTokenProvider;
import goorm.zzaturi.global.jwt.repository.TokenRepository;
import goorm.zzaturi.global.oauth2.OAuthInfoResponse;
import goorm.zzaturi.global.oauth2.OAuthLoginParams;
import goorm.zzaturi.global.oauth2.service.RequestOAuthInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberJpaRepository memberJpaRepository;
    private final TokenRepository tokenRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RequestOAuthInfoService requestOAuthInfoService;

    @Transactional
    public TokenDto login(OAuthLoginParams params) throws Exception {
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        String email = findOrCreateMember(oAuthInfoResponse);
        Member member = memberJpaRepository.findByEmail(email)
            .orElseThrow(Exception::new);

        TokenDto tokenDto = jwtTokenProvider.generate(email);
        saveOrUpdateToken(member, tokenDto);

        return tokenDto;
    }

    private String findOrCreateMember(OAuthInfoResponse oAuthInfoResponse) {
        return memberJpaRepository.findByEmail(oAuthInfoResponse.getEmail())
            .map(Member::getEmail)
            .orElseGet(() -> createNewMember(oAuthInfoResponse));
    }

    private String createNewMember(OAuthInfoResponse oAuthInfoResponse) {
        Member member = Member.builder()
            .email(oAuthInfoResponse.getEmail())
            .nickname(oAuthInfoResponse.getNickname())
            .socialType(oAuthInfoResponse.getOAuthProvider())
            .imageUrl(oAuthInfoResponse.getImageUrl())
            .build();

        return memberJpaRepository.save(member).getEmail();
    }

    private void saveOrUpdateToken(Member member, TokenDto tokenDto) {
        Token token = tokenRepository.findByMember(member)
            .orElseGet(() -> createNewToken(member, tokenDto));

        token.updateRefreshToken(tokenDto.getRefreshToken());
        tokenRepository.save(token);
    }

    private Token createNewToken(Member member, TokenDto tokenDto) {
        return Token.builder()
            .member(member)
            .refreshToken(tokenDto.getRefreshToken())
            .build();
    }

    @Transactional
    public TokenDto generateAccessToken(ReIssueRequest reIssueRequest)
        throws Exception {
        Token token = validateRefreshToken(reIssueRequest);

        Member member = memberJpaRepository.findById(token.getMember().getId())
            .orElseThrow(Exception::new);

        return createNewAccessToken(member, token.getRefreshToken());
    }

    private Token validateRefreshToken(ReIssueRequest reIssueRequest) throws Exception {
        Token token = tokenRepository.findByRefreshToken(reIssueRequest.getRefreshToken());
        if (token.getRefreshToken() == null) {
            throw new Exception();
        }
        return token;
    }

    private TokenDto createNewAccessToken(Member member, String refreshToken) {
        return TokenDto.builder()
            .grantType("Bearer")
            .accessToken(jwtTokenProvider.generateAccessToken(member.getEmail()))
            .refreshToken(refreshToken)
            .build();
    }
}
