package com.green.FetchStudent.service;

import com.green.FetchStudent.vo.ClassVO;
import com.green.FetchStudent.vo.ScoreVO;
import com.green.FetchStudent.vo.StuVO;

import java.util.List;

public interface StuService {
    List<StuVO> selectStu (StuVO stuVO); //메인

    List<ClassVO> selectClassList (); //select 반 선택 올리기

    ScoreVO selectScore (ScoreVO scoreVO); //학생 선택 후 점수 올리기

    void insertScore (ScoreVO scoreVO);
}
