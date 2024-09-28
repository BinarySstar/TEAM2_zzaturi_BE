package goorm.zzaturi.global.config;

import goorm.zzaturi.global.jwt.JwtAuthenticationEntryPoint;
import goorm.zzaturi.global.jwt.filter.JwtAuthorizationFilter;
import goorm.zzaturi.global.jwt.filter.JwtExceptionFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    private final JwtExceptionFilter jwtExceptionFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf
                .ignoringRequestMatchers(PathRequest.toH2Console()) // H2 콘솔 요청에 대해 CSRF 무시
                .disable() // 그 외 모든 요청에 대해 CSRF 보호 비활성화
            )
            .authorizeHttpRequests(authorize ->
                authorize
                    .requestMatchers("/**").permitAll() //TODO 지워야함
                    .requestMatchers("/h2-console/**").permitAll()
                    .anyRequest().authenticated())
            .exceptionHandling(
                handle -> handle.authenticationEntryPoint(new JwtAuthenticationEntryPoint()))
            .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(jwtExceptionFilter, JwtAuthorizationFilter.class)
            .build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return webSecurity -> webSecurity.ignoring()
            .requestMatchers("/swagger-ui/**", "/favicon.ico", "/api-docs/**", "/api/auth/**",
                "/ws/**", "/h2-console/**");
    }

}
