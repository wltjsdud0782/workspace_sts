package com.green.Board.controller;

import com.green.Board.service.MemberServiceImpl;
import com.green.Board.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Resource(name = "memberService") // name 값의 객체 불러오기
    private MemberServiceImpl memberService;

    @GetMapping("/login") //메인화면
    public String loginForm(){
        return "login";
    }

    @PostMapping("/loginSuccess") //로그인 성공화면
    public String loginSuccess(MemberVO memberVO, HttpSession session){
        MemberVO loginInfo = memberService.login(memberVO);
        //loginInfo 에는 id, name, admin 정보만 들어가있다.

        //조회된 정보가 null 이 아니고 존재한다면
        if (loginInfo != null){
            //세션에 정보 저장
            session.setAttribute("loginInfo", loginInfo);
        }
        return "login_success";
    }

    @GetMapping("logout") //로그아웃
    public String logout(HttpSession session){
        session.removeAttribute("loginInfo");
        return "redirect:/member/login";
    }

    @GetMapping("join") //회원가입화면
    public String join(){
        return "join";
    }

    @PostMapping("joinSuccess") //회원 등록 후 메인화면
    public String joinSuccess(MemberVO memberVO, Model model){
        memberService.insertMember(memberVO);
        model.addAttribute(memberVO);
        return "redirect:/member/login";
    }

}
