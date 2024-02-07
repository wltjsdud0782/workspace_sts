package com.green.Board.controller;

import com.green.Board.vo.MemberVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class SessionTestController {

    //URL : localhost:8081/test/
    @GetMapping("/page1")
    public String page1(HttpSession session){
        //세션에 데이터 저장하기 (.setAttribute)
        session.setAttribute("name", "java");
        session.setAttribute("age", 20);
        session.setAttribute("member", new MemberVO()); // VO안 모든 정보 출력

        //세션 유지시간 설정 : !클릭 반응이 없는! 초 단위 (60초 * ?분)으로 표기
        session.setMaxInactiveInterval(60 * 30);
        return "page1";
    }

    @GetMapping("/page2")
    public String page2(HttpSession session){
        //세션에 담긴 데이터 확인(.getAttribute)
        String name = (String) session.getAttribute("name"); //String 으로 형변환
        int age = (int) session.getAttribute("age"); //형변환2
        MemberVO vo = (MemberVO) session.getAttribute("member"); //형변환3

        //세션에 저장된 데이터 삭제 (.removeAttribute)
        session.removeAttribute("age");

        //세션에 저장된 '모든' 데이터 삭제 → ex) 로그아웃 (.invalidate)
        session.invalidate();

        return "page2";
    }

    @GetMapping("/page3")
    public String page3(){
        return "page3";
    }
}
