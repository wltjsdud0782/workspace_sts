package com.green.StudentManager.service;

import com.green.StudentManager.vo.StuVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stuService")
//StuServiceImpl stuService = new StuServiceImpl();
public class StuServiceImpl implements StuService{
    @Autowired
    //SqlSessionTemplate sqlSession = new SqlSessionTemplate();
    private SqlSessionTemplate sqlSession;

    @Override
    public int insertStu(StuVO stuVO) {
        int result = sqlSession.insert("insertStu", stuVO);
        return result;
    }

    @Override
    public List<StuVO> selectStuList() {
        return sqlSession.selectList("selectStuList");
    }

    @Override
    public StuVO selectStuInfo(int stuNo) {
        return sqlSession.selectOne("selectStuInfo", stuNo);
    }

    @Override
    public int deleteStu(int stuNo) {
        return sqlSession.delete("deleteStu", stuNo);
    }

}
