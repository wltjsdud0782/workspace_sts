package com.green.Shop.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;

// 로그인 실패 시 실행되는 클래스

// AuthenticationFailureHandler 의 인터페이스를 구현하는 클래스를 생성하면
// onAuthenticationFailure 메소드를 정의해야 한다. (로그인 실패 시 자동 호출)
@Component // ▶ 큰 역할이 없는 클래스의 객체 생성
public class LoginFailHandler implements AuthenticationFailureHandler {

    // 로그인 실패 시 자동 호출 되는 메소드
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        String errorMsg = "";
        if (exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException){
            errorMsg = "아이디 또는 비밀번호가 틀립니다.";
        }
        else if(exception instanceof UsernameNotFoundException){
            errorMsg = "존재하지 않는 사용자 ID 입니다.";
        }
        else {
            errorMsg = "알 수 없는 이유로 로그인 실패했습니다. 관리자에게 문의하세요.";
        }

        // 한글 인코딩 변환
        errorMsg = URLEncoder.encode(errorMsg, "UTF-8");


        // 매개변수를 사용해 이동할 페이지 설정
        response.sendRedirect("/member/login?errorMsg="+errorMsg);
    }
}
