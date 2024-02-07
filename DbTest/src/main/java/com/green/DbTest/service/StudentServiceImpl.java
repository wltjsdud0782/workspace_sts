package com.green.DbTest.service;

import com.green.DbTest.vo.StudentVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("stuService")
public class StudentServiceImpl implements StudentService{

    //Mybatis 에서 제공하는 db 쿼리 기능이 정의되어 있는 객체
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    //학생 한 명 저장 기능
    //db 작업 : 조회, 삽입, 삭제, 수정      [] 생략 가능
    //조회 : (1) sqlSession.selectOne("실행할 쿼리 id", [쿼리에서 빈 값을 채울 데이터]);
    //                          → 조회 결과 데이터가 0행 or 1행일 경우  (ex.primary key 조회)
    //       (2) sqlSession.selectList("실행할 쿼리 id", [쿼리에서 빈 값을 채울 데이터]);
    //                          → 조회 할 때마다 조회되는 행의 개수가 매번 다른 경우
    //삽입 : sqlSession.insert("실행할 쿼리 id", [쿼리에서 빈 값을 채울 데이터]);
    //삭제 : sqlSession.delete("실행할 쿼리 id", [쿼리에서 빈 값을 채울 데이터]);
    //수정 : sqlSession.update("실행할 쿼리 id", [쿼리에서 빈 값을 채울 데이터]);

    public void insertStudent() {
        sqlSession.insert("insertStudent"); //xml 파일 속 id
    }

    @Override
    public void deleteStudent() {
        sqlSession.delete("deleteStudent");
    }

    @Override
    public void delete(int stuNo) {
        sqlSession.delete("delete", stuNo);
    }

    //정보가 조회되는 select 는 저장할 공간도 만들어 줘야 한다.
    @Override
    public String selectName() {
        String name = sqlSession.selectOne("selectName");
        return name;
    }

    @Override
    public int selectScore() {
        int score = sqlSession.selectOne("selectScore");
        return score;
    }

    @Override
    public StudentVO selectStu() {
        StudentVO stu = sqlSession.selectOne("selectStu");
        return stu;
    }

    @Override
    public List<StudentVO> selectStuList() {
        List<StudentVO> list = sqlSession.selectList("selectStuList");
        return list;
    }


}
