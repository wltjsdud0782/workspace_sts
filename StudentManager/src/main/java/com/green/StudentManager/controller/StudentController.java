package com.green.StudentManager.controller;

import com.green.StudentManager.service.StuService;
import com.green.StudentManager.service.StuServiceImpl;
import com.green.StudentManager.vo.StuVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.Name;
import java.util.List;

@Controller
public class StudentController {
    @Resource(name = "stuService")
    //StuServiceImpl stuService = new StudentController;
    private StuServiceImpl stuService;

    //학생 목록 페이지로 이동
    @GetMapping("/")
    public String stuList(Model model){
        //학생 목록 조회 후 정보를 html 로 전달
        List<StuVO> list = stuService.selectStuList(); //조회
        model.addAttribute("stuList", list);

        return "stu_list";
    }

    @GetMapping("/regStu")
    public String goReg(){
        return "reg_student";
    }

    @PostMapping("/regStu")  //GetMapping 과 PostMapping 은 다르기에 같은 주소 가능하다.
    public String reg(StuVO stuVO){
        //학생 등록
        stuService.insertStu(stuVO);

        return "redirect:/";  //redirect : 다시 컨트롤러 "/" 페이지로 돌리겠습니다.
    }

    @GetMapping("/stuInfo")
    public String studentDetail(@RequestParam(name = "stuNo") int stuNo, Model model){
        //선택한 학생의 학번으로 정보 조회
        StuVO detail = stuService.selectStuInfo(stuNo);
        model.addAttribute("stuInfo", detail);
        return "student_detail";
    }

    @GetMapping("/deleteStu")
    public String deleteStu(StuVO stuVO){
        stuService.deleteStu(stuVO.getStuNo());
        return "redirect:/";
    }

}
