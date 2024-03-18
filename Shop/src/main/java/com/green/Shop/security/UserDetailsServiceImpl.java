package com.green.Shop.security;

import com.green.Shop.member.service.MemberService;
import com.green.Shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource(name = "memberService")
    private MemberService memberService;

    // Security 가 로그인 프로세스를 진행하면 가장 먼저 호출되는 메소드
    // 실제 로그인 처리 메소드 X
    // Security 가 로그인을 처리할 수 있도록 로그인 정보를 전달해주는 역할
    @Override
    public UserDetails loadUserByUsername(String username) {
        // 로그인을 시도하는 유저의 정보 조회
        MemberVO loginInfo = memberService.selectMember(username);
//        if (loginInfo == null){
//            throw new BadCredentialsException("error");
//        }
        
        // 로그인을 시도하는 유저의 정보를 security 에게 넘기기
        // user 는 userdetails 라는 인터페이스를 구현하고 있다.
        // user 는 자식 userdetails 는 부모
        User user =(User) User.builder()
                .username(loginInfo.getMemberId())
                .password("{noop}" + loginInfo.getMemberPw())
                .roles(loginInfo.getMemberRoll())
                .build();
        
        return user;
    }
}
