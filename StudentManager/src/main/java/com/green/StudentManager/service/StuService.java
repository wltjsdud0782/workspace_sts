package com.green.StudentManager.service;

import com.green.StudentManager.vo.StuVO;

import java.util.List;

public interface StuService {

    //return 타입 : 쿼리 실행 결과를 받아옴.
    //  (1) SELECT 리턴 : 조회가 되는 형식 따라 매번 바뀐다. (0행 or 1행 : vo 클래스) (else : List<vo>)
    //  (2) INSERT , DELETE , UPDATE 의 리턴 : void / int (정석) > 조회가 아니고 '영향받은 행' 갯수가 기준이기 때문에 int 가 정석이다.

    //매개 변수 : 쿼리 실행의 빈 값 채우는 역할.
    //빈 값을 채울 데이터 갯수가
    //  (1) 0개 : X
    //  (2) 1개 : 숫자 - int 자료형 하나       /       문자열 - String 자료형 하나
    //  (3) 여러개 : vo 객체

    //학생 등록
    int insertStu(StuVO stuVO);

    //학생 전체 조회
    List<StuVO> selectStuList();

    //선택한 학생 상세정보 조회
    StuVO selectStuInfo(int stuNo);

    //선택한 학생 지우기
    int deleteStu(int stuNo);


}
