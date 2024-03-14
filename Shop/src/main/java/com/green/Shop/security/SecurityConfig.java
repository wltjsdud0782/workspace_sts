package com.green.Shop.security;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// 인증 및 인가 설정 클래스
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    @Autowired
    private LoginFailHandler loginFailHandler;

    // 인증과 인가에 대한 설정 내용이 있는 메소드 구현
    // 반드시 리턴타입은 securityFilterChain
    // 메소드의 매개변수로 httpsecurity 객체 필요
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception{

        security.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        c -> {
                            c.requestMatchers(
                                        new AntPathRequestMatcher("/")
                                        , new AntPathRequestMatcher("/item/list")
                                        , new AntPathRequestMatcher("/member/login")
                                        , new AntPathRequestMatcher("/member/loginSuccess")
                                        , new AntPathRequestMatcher("/member/join")
                                    ).permitAll()
                                    .requestMatchers(
                                            new AntPathRequestMatcher("/admin/**")
                                    ).hasRole("ADMIN")
                                    .anyRequest().authenticated();
                        }
                )
                .formLogin(
                        formLogin -> {
                            formLogin.loginPage("/member/login")
                                    .loginProcessingUrl("/member/loginSuccess")
                                    //.defaultSuccessUrl("/", true)
                                    // true - ""안 페이지이동 / false - 이전페이지이동
                                    //.failureUrl("/member/login")
                                    .usernameParameter("memberId")
                                    .passwordParameter("memberPw")
                                    // ▼ 로그인 성공 시 실행시킬 클래스의 객체
                                    .successHandler(loginSuccessHandler)
                                    // ▼ 로그인 실패 시 실행시킬 클래스의 객체
                                    .failureHandler(loginFailHandler);
                        }
                )
                .logout(
                        logout -> {

                        }
                );

        return security.build();
    }

    // 정적인 자원은 security 가 인증 및 인가 관리에서 제외하도록 설정
    // 정적인 자원 : .js 파일, .css 파일, 이미지 파일 등등
    // resources 폴더 밑 모든 파일들
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> web.ignoring().requestMatchers(
                new AntPathRequestMatcher("/upload/**")
                , new AntPathRequestMatcher("/css/**")
                , new AntPathRequestMatcher("/js/**")
                , new AntPathRequestMatcher("/images/**")
                , new AntPathRequestMatcher("/favicon.ico")
//              다 지우고 new AntPathRequestMatcher("/**") 가능
        );
    }
}
