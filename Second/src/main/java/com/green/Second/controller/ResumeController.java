package com.green.Second.controller;

import com.green.Second.VO.ResumeVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResumeController {

    @GetMapping("/resume")
    public String first(){
        return "resume_1";
    }

    @PostMapping("/go_resume_2")
    public String second(@RequestParam(name = "name")String name,
                           @RequestParam(name = "tel")String tel, Model model){
        model.addAttribute("name", name);
        model.addAttribute("tel", tel);

        return "resume_2";
    }

    @PostMapping("/go_resume_3")
    public String third(@RequestParam(name = "name")String name,
                           @RequestParam(name = "tel")String tel,
                           @RequestParam(name = "grade")String grade,
                           @RequestParam(name = "schoolName")String schoolName,
                           @RequestParam(name = "type")String type,
                           @RequestParam(name = "certiName", required = false)String certiName,
                           @RequestParam(name = "certiDate", required = false)String certiDate,
                           @RequestParam(name = "certiAt", required = false)String certiAt,
                           @RequestParam(name = "careerName", required = false)String careerName,
                           @RequestParam(name = "careerJob", required = false)String careerJob,
                           @RequestParam(name = "careerDate1", required = false)String careerDate1,
                           @RequestParam(name = "careerDate2", required = false)String careerDate2,
                           @RequestParam(name = "intro")String intro,
                           //ResumeVO resumeVO,
                           Model model){

        String certiAll =  certiName + "\t" + certiDate + "\t" + certiAt;
        //String certiAll =  resumeVO.getCertiName() + "\t" + resumeVO.getCertiDate() + "\t" + resumeVO.getCertiAt();
        String careerAll = careerName + "\t" + careerJob + "\t" + careerDate1 + "년" + careerDate2 + "개월";
        //String careerAll =  resumeVO.getCareerName() + "\t" + resumeVO.getCareerJob() + "\t" + resumeVO.getCareerDate1() + "년" + resumeVO.getCareerDate2() + "개월";

        model.addAttribute("name", name);
        model.addAttribute("tel", tel);
        model.addAttribute("grade", grade);
        model.addAttribute("schoolName", schoolName);
        model.addAttribute("type", type);
        model.addAttribute("certiAll", certiAll);
        model.addAttribute("careerAll", careerAll);
        model.addAttribute("intro", intro);

        return "resume_3";
    }
}
