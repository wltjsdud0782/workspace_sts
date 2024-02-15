package com.green.Shop.study.fetch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/fetch")
public class FetchController {

    @GetMapping("/main")
    public String main(){
        return "test/fetch/main";
    }

    // 넘어오는 데이터를 받을 때 사용하는 @ 어노테이션
    // 1. @RequestParam = URL에 데이터가 함께 넘어올 때 사용
        // localhost:8081/main?a=10 > a=10   ※a태그 , form태그※
    // 2. @RequestBody = URL외 다른 유형으로 데이터가 넘어올 때 사용
        //
    @ResponseBody
    @PostMapping("/fetch1")
    public void fetch1(@RequestBody MemberVO memberVO){
        System.out.println("fetch1 메소드 실행");
        System.out.println(memberVO);
    }

    @ResponseBody
    @PostMapping("/fetch2")
    public void fetch2(@RequestBody HashMap<String, String> data){
        System.out.println("fetch2 메소드 실행");
        System.out.println(data);

        System.out.println(data.get("id"));
        System.out.println(data.get("name"));
        System.out.println(data.get("age"));
    }

    //자바스크립트에서 배열이 넘어오면 ArrayList 로 받을 수 있다.
    @ResponseBody
    @PostMapping("/fetch3")
    public void fetch3(@RequestBody ArrayList<MemberVO> list){
        System.out.println("fetch3 메소드 실행");
        System.out.println(list);
    }

    @ResponseBody
    @PostMapping("/fetch4")
    public void fetch4(@RequestBody HashMap<String, Object> map){
        System.out.println("fetch4 메소드 실행");
        System.out.println(map);

        System.out.println(map.get("cartCode"));

        HashMap<String, String> a = (HashMap<String, String>)map.get("memberInfo");
        System.out.println(a.get("memberId"));
    }


}
