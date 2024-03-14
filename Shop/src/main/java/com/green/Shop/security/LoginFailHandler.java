package com.green.Shop.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

// 로그인 실패 시 실행되는 클래스

// AuthenticationFailureHandler 의 인터페이스를 구현하는 클래스를 생성하면
// onAuthenticationFailure 메소드를 정의해야 한다. (로그인 실패 시 자동 호출)
@Component // ▶ 큰 역할이 없는 클래스의 객체 생성
public class LoginFailHandler implements AuthenticationFailureHandler {

    // 로그인 실패 시 자동 호출 되는 메소드
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }
}
