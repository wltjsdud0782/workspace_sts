package com.green.FetchStudent.controller;

import com.green.FetchStudent.service.StuService;
import com.green.FetchStudent.service.StuServiceImpl;
import com.green.FetchStudent.vo.ClassVO;
import com.green.FetchStudent.vo.ScoreVO;
import com.green.FetchStudent.vo.StuVO;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.CallSite;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/stu")
public class StuController {
    @Resource (name = "stuService")
    private StuServiceImpl stuService;

    @GetMapping ("/main") //메인화면
    public String main(StuVO stuVO, Model model){
        //반 선택
        model.addAttribute("classList", stuService.selectClassList());
        //정보 조회
        model.addAttribute("stuList",stuService.selectStu(stuVO));
        return "stu_manage";
    }

    //비동기
    @PostMapping("/fetchSelect")
    @ResponseBody
    public List<StuVO> fetchSelect(StuVO stuVO){
        List<StuVO> stuList = stuService.selectStu(stuVO);

        return stuList;
    }

    //선택한 학생 점수 표시
    @PostMapping("/selectScore")
    @ResponseBody
    public ScoreVO selectScore(ScoreVO scoreVO){
        ScoreVO stuScore = stuService.selectScore(scoreVO);

        return stuScore;
    }

    //학생 점수 저장
    @PostMapping("/insertScore")
    @ResponseBody
    public void insertScore(ScoreVO scoreVO){
        stuService.insertScore(scoreVO);
    }
}
