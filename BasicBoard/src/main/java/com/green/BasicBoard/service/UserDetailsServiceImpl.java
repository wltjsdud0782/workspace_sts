package com.green.BasicBoard.service;

import com.green.BasicBoard.vo.MemberVO;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 스프링 시큐리티가 제공하는 로그인 기능을 구현하는 클래스
// UserDetailsService 라는 인터페이스 상속 및 구현
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource(name = "boardService")
    private BoardServiceImpl boardService;

    // 시큐리티가 로그인할 때 알아서 호출하는 메소드
    // 매개변수로 전달되는 문자열은 로그인을 시도하는 회원의 id
    // 로그인페이지의 id 입력하는 input 태그의 name 속성값을 'username'으로 설정.
    // name 속성값을 password로 지정하면 해당 input 태그를 비밀번호 데이터로 간주.
    
    // 로그인을 실행하는 url이 호출되면 아레 메소드 자동 실행
    // 로그인 하려는 회원의 필요한 정보를 조회해서 시큐리티에게 전달하는 코드 작성
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 로그인 하려는 회원의 정보 조회
        MemberVO member = boardService.login(username);

        // 조회한 정보를 UserDetails 타입 변환
        UserDetails userInfo = User.builder()
                                .username(member.getMemberId())
                                // 비밀번호를 시큐리티에게 전달하면
                                // 기본적으로 시큐리티는 비밀번호가 암호화되어 있다고 판단
                                // {noop}을 비밀번호 앞에 문자열로 추가하면
                                // 암호화 되어 있지 않다는 것을 알려준다.
                                .password(member.getMemberPw())
                                .roles(member.getMemberRoll())
                                .build();
        return userInfo;
    }
}
