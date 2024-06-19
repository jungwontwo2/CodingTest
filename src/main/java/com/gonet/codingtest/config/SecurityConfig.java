package com.gonet.codingtest.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationFailureHandler CustomAuthFailureHandler;

    @Bean//비밀번호 암호화
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean//시큐리티 필터
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/users").authenticated()//로그인 했으면 가능
                .anyRequest().permitAll()//나머지는 그냥 가능
        );

        http.formLogin((auth) -> auth.loginPage("/users/login")//로그인 페이지
                .loginProcessingUrl("/users/login")//포스트 보내면 어디로 가는지
                .usernameParameter("username")
                .failureHandler(CustomAuthFailureHandler)
                .defaultSuccessUrl("/")
                .permitAll());


        http.logout((logout)->logout.logoutRequestMatcher(new AntPathRequestMatcher("/users/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true));

        http.csrf((auth)->auth.disable());


        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .requestMatchers("/css/**", "style.css");
    }

}
