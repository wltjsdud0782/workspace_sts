package com.green.FetchStudent.controller;


//비동기 통신 학습용 컨트롤러
// javascript ▶ fetch
// jquery ▶ ajax
// react ▶ axios

import com.green.FetchStudent.vo.StuVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/study")
public class FetchController {

    @GetMapping("/t1")
    public String t1(){
              return "fetch_test";
    }

    @PostMapping("/t2")
    @ResponseBody //해당 메소드 비동기 통신 사용 && return 페이지 전환 X 알려주는 어노테이션
    public String t2(@RequestParam (name = "name")String name,
                     @RequestParam (name = "age")int age){ //string ▶ void 변경
        System.out.println("t1 메소드 실행");
        System.out.println(name + " " + age);
        return "Hello";
    }

    @PostMapping("/t3")
    @ResponseBody
    public StuVO t3(StuVO vo){
        System.out.println("t3 메소드 실행");
        System.out.println(vo);

        StuVO stuVO = new StuVO();
        stuVO.setStuNum(1);
        stuVO.setStuName("김자바");
        stuVO.setClassCode(1);
        stuVO.setClassName("자바반");

        return stuVO;

    }

    @PostMapping("/t4")
    @ResponseBody
    public List<StuVO> t4(){
        System.out.println("t4 메소드 실행");

        List<StuVO> list = new ArrayList<>();

        for (int i = 1; i < 6; i++) {
            StuVO stuVO = new StuVO();
            stuVO.setStuNum(i);
            stuVO.setStuName("자바_" + i);
            stuVO.setClassCode(1 + i);
            stuVO.setClassName("자바반_" + i);
            list.add(stuVO);
        }

        return  list;
    }

}
