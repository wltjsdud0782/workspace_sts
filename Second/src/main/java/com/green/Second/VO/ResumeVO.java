package com.green.Second.VO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResumeVO {
    private String name;
    private String tel;
    private String grade;
    private String schoolName;
    private String type;
    private String certiName;
    private String certiDate;
    private String certiAt;
    private String careerName;
    private String careerJob;
    private String careerDate1;
    private String careerDate2;
    private String intro;

    public ResumeVO(String name, String tel, String grade, String schoolName, String type, String certiName, String certiDate, String certiAt, String careerName, String careerJob, String careerDate1, String careerDate2, String intro) {
        this.name = name;
        this.tel = tel;
        this.grade = grade;
        this.schoolName = schoolName;
        this.type = type;
        this.certiName = certiName;
        this.certiDate = certiDate;
        this.certiAt = certiAt;
        this.careerName = careerName;
        this.careerJob = careerJob;
        this.careerDate1 = careerDate1;
        this.careerDate2 = careerDate2;
        this.intro = intro;
    }
}