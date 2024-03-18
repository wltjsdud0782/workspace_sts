package com.green.Shop.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// 로그인 성공 시 실행되는 클래스
@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    // 로그인 성공 시 자동 호출 되는 메소드
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        setDefaultTargetUrl("/"); // 로그인 성공 시 이동 페이지

        // 로그인 시 필요한 코드 작성공간 ex)세션 데이터 저장
//        HttpSession session = request.getSession();
//        session.setAttribute();
//        session.getAttribute("loginInfo");

        // 로그인 정보를 통한 로직 구성
        User user = (User) authentication.getPrincipal(); // 로그인 한 사람의 정보 들고오기
        List<GrantedAuthority> authorityList = new ArrayList<>(user.getAuthorities());
        List<String> aList = new ArrayList<>();
        for(GrantedAuthority authority : authorityList){
            aList.add(authority.getAuthority());
        }
        boolean b = aList.contains("ADMIN");
        if (b){
            redirectStrategy.sendRedirect(request, response, "/admin/buyHistory");
        }


        if(savedRequest != null){ // 이전 페이지 or 가려던 페이지 있을 때 이동 코드
            String targetUrl = savedRequest.getRedirectUrl();
            redirectStrategy.sendRedirect(request, response, targetUrl);
        }else{ // 없을 때 이동 코드
            redirectStrategy.sendRedirect(request, response, getDefaultTargetUrl());
        }
    }
}
