package com.green.BasicBoard.controller;

import com.green.BasicBoard.service.BoardServiceImpl;
import com.green.BasicBoard.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @Resource(name = "boardService")
    private BoardServiceImpl boardService;
    @Autowired
    private BCryptPasswordEncoder encoder;

    //회원가입 페이지
    @GetMapping("/joinForm")
    public String joinForm(){
        return "join";
    }

    // 회원가입
    @PostMapping("/join")
    public String join(MemberVO memberVO){
        // 비밀번호 암호화
        memberVO.setMemberPw(encoder.encode(memberVO.getMemberPw()));

        boardService.join(memberVO);
        return "redirect:/loginForm";
    }

    //로그인 페이지
    @GetMapping("/loginForm")
    public String loginForm(){
        return "login";
    }

    // 로그인


    // 로그아웃



}
