package com.green.First.controller;

import com.green.First.vo.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Member;

//@가 붙은 언어 : anotation
@Controller
public class FirstController {

    @GetMapping("main") //주소창 뒤
    public String main(){
        return "first"; //파일명 뒤에 확장자 X
    }

    //@RequestParam : html 에서 넘어오는 데이터를 받을 때 사용한다.
    //name : 넘어오는 데이터의 이름
    @GetMapping("second")
    public String second(@RequestParam(name = "name") String name
                        ,@RequestParam(name = "age") int age){
        System.out.println("@@@@@@" + name + " " + age);
        return "abc";
    }

    //넘어오는 데이터의 이름과 동일한 변수를 가진 '클래스 객체'로 데이터를 받을 수 있다.
    @GetMapping("third")
    public String third(MemberVO memberVO, Model model){
        System.out.println(memberVO);
        //받은 데이터를 html로 전달
        model.addAttribute("score", 80);
        return "abc";
    }
}
