package com.green.BasicBoard.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
// /member/* > /member/a, /member/b 처럼 한 단계 까지 가능
// /member/** > /member/a/1, /member/b/1 처럼 두 단계 이상까지 가능

// /admin/?????????????

// spring 시큐리티 인증, 인가에 대한 프로세스 정의

@EnableWebSecurity // 이 클래스가 시큐리티 설정 파일임을 인지시켜줌.
@Configuration // 객체 생성 어노테이션 (@Controller, @Service)
public class SecurityConfig {

    // 암호화에 사용하는 객체 생성
    @Bean
    public BCryptPasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
    }


    // 메소드의 실행 결과 리턴되는 데이터를 객체로 생성
    @Bean // 객체 생성 어노테이션
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
//        // csrf : csrf 공격에 대해 어떻게 대처할 것?
//        security.csrf(AbstractHttpConfigurer::disable)
//                // 아래 메소드 안 인증, 인가에 대한 모든 설정 작성
//                .authorizeHttpRequests(
//                        c -> {
//                            c.requestMatchers(
//                                    new AntPathRequestMatcher("/")
//                                    , new AntPathRequestMatcher("/loginForm")
//                                    , new AntPathRequestMatcher("/joinForm")
//                                    ).permitAll()
//                                    .anyRequest().authenticated();
//                        }
//                )
//                // 로그인 설정 + 로그인 할 때 form 방식 이용.
//                .formLogin(
//                        formLogin -> {
//                            // 로그인 페이지로 이동하는 url 설정
//                            formLogin.loginPage("/loginForm")
//                                    // 실제 로그인 기능 실행 url 설정
//                                    .loginProcessingUrl("/login")
//                                    // 로그인 성공 시 기본 이동 url 설정
//                                    .defaultSuccessUrl("/");
//                        }
//                );
//
        // csrf 공격에 대한 방어 해지
        security.csrf(AbstractHttpConfigurer::disable)
                // authorizeHttpRequests : 메소드 안 인증 및 인가 관리
                .authorizeHttpRequests(
                        c -> {
//                            c.anyRequest().permitAll();
                            c.requestMatchers(
                                    new AntPathRequestMatcher("/")
                                    , new AntPathRequestMatcher("/loginForm")
                                    , new AntPathRequestMatcher("/login")
                                    , new AntPathRequestMatcher("/joinForm")
                                    , new AntPathRequestMatcher("/join")
                                    , new AntPathRequestMatcher("/sample")
                            ).permitAll()
                            .requestMatchers(
                                    new AntPathRequestMatcher("/admin")
                                    // 권한 앞에 ROLL_ 이 붙어버려서 적을때도 붙여줘야한다.
                            ).hasRole("ADMIN")
                            .requestMatchers(
                                    new AntPathRequestMatcher("/manager")
                            ).hasRole("MANAGER")
                            .requestMatchers(
                                    new AntPathRequestMatcher("/boardWriteForm")
                            ).hasAnyRole("USER", "MANAGER")
                             .anyRequest().authenticated();
                        }
                )
                // 로그인 form 활용할 것, 설정 내용 포함
                .formLogin(
                        formLogin -> {
                            // 로그인 url 설정
                            formLogin.loginPage("/loginForm")
                                    // 로그인 시 전달되는 id 및 pw 의 name 속성값 지정
                                    .usernameParameter("memberId")
                                    .passwordParameter("memberPw")
                                    // 로그인 기능 url
                                    .loginProcessingUrl("/login")
                                    // 로그인 성공 시 이동 url
                                    // 두 번째 매개변수로 true : 항상 지정한 url 이동
                                    // 매개변수 X, false : 이전 페이지 이동, 없다면 지정한 url 이동
                                    .defaultSuccessUrl("/")
                                    .failureUrl("/loginForm");
                        }
                ).logout(
                        logout -> {
                            // 로그아웃 기능 url 
                            logout.logoutUrl("/logout")
                                    // 로그아웃 성공시 url
                                    .logoutSuccessUrl("/")
                                    // 로그아웃 성공시 session 데이터 삭제
                                    .invalidateHttpSession(true);
                        }
                ).exceptionHandling( // 예외 발생 시 처리 할 내용
                        ex -> {
                            ex.accessDeniedPage("/deny");
                        }
                );


        return security.build();
    }
}
