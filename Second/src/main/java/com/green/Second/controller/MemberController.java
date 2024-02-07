package com.green.Second.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@Controller : 해당 클래스 파일이 컨트롤러 역할을 하는 클래스임을 spring 에서 인식.
@Controller
public class MemberController {

    //@GetMapping, @PostMapping - 페이지 접속 정보
    //소괄호안의 글자와 localhost:8081 뒤의 글자가 일치하면 해당 메소드를 실행
    //@PostMapping : 페이지 이동 방법 중 form 태그로 이동 및 form 태그의 method 속성값이 post 일 때만 실행된다.
    //@GetMapping : (1)method 속성값이 get 일 때 (2)a 태그로 이동될 때 (3)주소창에 url을 직접 입력할 때

    @GetMapping("/")
    public String main(){
        //리턴 되는 문자열은 마지막에 실행되는 html 파일명이다.
        //html 파일은 반드시 templates 폴더 안에 존재한다.

        return "first"; //first.html 실행
    }

    //@RequestParam : html에서 넘어오는 데이터를 전달 받는다.
    //@RequestParam 속성
    // (1) name : html에서 넘어오는 데이터의 이름
    // (2) required : 넘어올수도있고 아닐수도 있는 값 설정 ( = false)
    //              (적지 않았을 땐 default 값은 true로 무조건 넘어오는 값이라고 정해진다.)
    // (3) defaultValue : 넘어오지 않았을 때 적용하는 기본값

    //html로 데이터를 전달하기 위해서는 메소드의 매개변수로 (I)Model의 객체를 선언해야 한다.
    @GetMapping("/second")
    public String second(@RequestParam(name = "addr", required = false)String address
                    , @RequestParam(name = "age", required = false, defaultValue = "1")int age
                    , Model model){
        System.out.println("addr = " + address);
        System.out.println("age = " + age);

        //html로 데이터 전달하기
        model.addAttribute("addr", address);
        model.addAttribute("age", age);
        model.addAttribute("name", "홍길동");

        return "second";
    }

}

