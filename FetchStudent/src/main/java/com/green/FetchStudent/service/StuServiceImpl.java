package com.green.FetchStudent.service;

import com.green.FetchStudent.vo.ClassVO;
import com.green.FetchStudent.vo.ScoreVO;
import com.green.FetchStudent.vo.StuVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stuService")
public class StuServiceImpl implements StuService{
    @Autowired
    private SqlSessionTemplate sqlSession;


    @Override //메인화면
    public List<StuVO> selectStu(StuVO stuVO) {
        return sqlSession.selectList("stuMapper.selectStu", stuVO);
    }

    @Override //반 선택
    public List<ClassVO> selectClassList() {
        return sqlSession.selectList("stuMapper.selectClassList");
    }

    @Override
    public ScoreVO selectScore(ScoreVO scoreVO) {
        return sqlSession.selectOne("stuMapper.selectScore", scoreVO);
    }

    @Override
    public void insertScore(ScoreVO scoreVO) {
        sqlSession.insert("stuMapper.insertScore", scoreVO);
    }
}

