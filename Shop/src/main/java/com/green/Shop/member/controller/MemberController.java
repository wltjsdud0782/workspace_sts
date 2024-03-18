package com.green.Shop.member.controller;

import com.green.Shop.member.service.MemberService;
import com.green.Shop.member.service.MemberServiceImpl;
import com.green.Shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Resource (name = "memberService")
    private MemberServiceImpl memberService;

    @PostMapping("/insertMember")
    public String insertMember(MemberVO memberVO, Model model){
        // 이메일 출력 시 , 값을 빈 값으로 변경 후 셋팅
        String memberEmail = memberVO.getMemberEmail();
        memberEmail = memberEmail.replace(",", "");
        memberVO.setMemberEmail(memberEmail);
        // memberVO.setMemberEmail(memberVO.getMemberEmail().replace(",".""));

        // 번호 출력 시 , 값을 - 으로 변경 후 셋팅
        String memberTel = memberVO.getMemberTel();
        memberTel = memberTel.replace(",", "-");
        memberVO.setMemberTel(memberTel);

        memberService.insertMember(memberVO);
        model.addAttribute(memberVO);
        return "redirect:/item/list";
    }

    @GetMapping("/login")
    public String login(@RequestParam(name = "errorMsg", required = false, defaultValue = "success")String errorMsg, Model model){
        model.addAttribute("errorMsg", errorMsg);
        return "content/member/login";
    }

//    @PostMapping("/loginSuccess")
//    public String loginSuccess(MemberVO memberVO, HttpSession session){
//        MemberVO loginInfo = memberService.selectMember(memberVO);
//
//        if (loginInfo != null){
//            session.setAttribute("loginInfo", loginInfo);
//        }
//        return "content/member/loginSuccess";
//    }

    //비동기 로그인
//    @PostMapping("/loginFetch")
//    @ResponseBody
//    public String loginFetch(MemberVO memberVO, HttpSession session){
//        MemberVO loginInfo = memberService.selectMember(memberVO);
//
//        if (loginInfo != null){
//            session.setAttribute("loginInfo", loginInfo);
//        }
//
//        return loginInfo == null ? "" : loginInfo.getMemberName();
//    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginInfo");

        return "redirect:/item/list";
    }
}
