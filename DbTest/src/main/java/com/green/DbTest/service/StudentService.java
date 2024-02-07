package com.green.DbTest.service;

import com.green.DbTest.vo.StudentVO;

import java.util.List;

//Interface : 기능 정의 모음집
public interface StudentService {

    //학생 한 명을 저장하는 기능 (db의 TABLE 에 저장)
    void insertStudent(); //public 필수 X

    //학생 한 명을 삭제하는 기능
    void deleteStudent();

    //입력받은 값으로 학생 삭제
    void delete(int stuNo);

    //학번이 1번인 학생 이름 조회 <정보를 받기 때문에 리턴 타입 필요>
    String selectName();

    //학번이 1번인 학생 점수 조회
    int selectScore();

    //학번이 1번인 학생 조회
    StudentVO selectStu();

    //학생 목록 조회
    List<StudentVO> selectStuList();
}
