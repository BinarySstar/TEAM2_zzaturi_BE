package goorm.zzaturi.domain.auth.controller;

import goorm.zzaturi.domain.auth.dto.request.ReIssueRequest;
import goorm.zzaturi.domain.auth.service.AuthService;
import goorm.zzaturi.global.jwt.dto.TokenDto;
import goorm.zzaturi.global.oauth2.kakao.KakaoLoginParams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/kakao")
    public ResponseEntity<TokenDto> loginKakao(@RequestBody KakaoLoginParams params)
        throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(params));
    }

    @PostMapping("/access")
    public ResponseEntity<TokenDto> newAccessToken(@RequestBody ReIssueRequest reIssueRequest)
        throws Exception {
        TokenDto newToken = authService.generateAccessToken(reIssueRequest);
        return ResponseEntity.status(HttpStatus.OK).body(newToken);
    }
}