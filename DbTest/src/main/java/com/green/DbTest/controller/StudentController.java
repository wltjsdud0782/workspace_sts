package com.green.DbTest.controller;

import com.green.DbTest.service.StudentService;
import com.green.DbTest.vo.StudentVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {
    @Resource(name = "stuService")
    private StudentService studentService;

    //localhost:8081
    @GetMapping("/")
    public String insertTest(){
        //학생 한 명 등록
        //studentService.insertStudent();
        //학생 한 명 삭제
        //studentService.deleteStudent();
//학번이 1인 학생 이름과 점수 조회
//        String name = studentService.selectName();
//        int score = studentService.selectScore();
//        System.out.println("name = " + name);
//        System.out.println("score = " + score);
        //학번이 1인 학생 정보 조회
//        StudentVO vo = studentService.selectStu();
//        System.out.println(vo);
    //학생 전체 정보 조회
        List<StudentVO> list = studentService.selectStuList();
        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
        return "insert";
    }

    @GetMapping("/delete")
    public String delete(){
        return "delete";
    }

    @PostMapping("/deleteResult")
    public String deleteResult(@RequestParam (name = "stuNo") int stuNo){
    //학생 삭제 기능
        studentService.delete(stuNo);
        return "delete_result";
    }


}
