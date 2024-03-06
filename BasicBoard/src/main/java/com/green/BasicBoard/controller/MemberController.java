package com.green.BasicBoard.controller;

import com.green.BasicBoard.service.BoardServiceImpl;
import com.green.BasicBoard.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @Resource(name = "boardService")
    private BoardServiceImpl boardService;

    //회원가입 페이지
    @GetMapping("/joinForm")
    public String joinForm(){
        return "join";
    }

    // 회원가입
    @PostMapping("/join")
    public String join(MemberVO memberVO){
        boardService.join(memberVO);
        return "redirect:/loginForm";
    }

    //로그인 페이지
    @GetMapping("/loginForm")
    public String loginForm(){
        return "login";
    }

    // 로그인
    @PostMapping("login")
    public String login(MemberVO memberVO, HttpSession session){
        MemberVO loginInfo = boardService.login(memberVO);

        if (loginInfo != null){
            session.setAttribute("loginInfo", loginInfo);
        }

        return "redirect:/";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginInfo");
        return "redirect:/";
    }
}
