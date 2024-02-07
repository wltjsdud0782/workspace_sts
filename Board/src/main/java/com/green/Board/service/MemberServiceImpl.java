package com.green.Board.service;

import com.green.Board.vo.MemberVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memberService") //Impl 의 객체 생성
public class MemberServiceImpl implements MemberService{
    @Autowired //Impl 파일 필수 > xml 파일 sqlSession 자동생성
    private SqlSessionTemplate sqlSession;

    @Override
    public void insertMember(MemberVO memberVO) {
        sqlSession.insert("memberMapper.insertMember", memberVO);
    }

    @Override
    public MemberVO login(MemberVO memberVO) {
        return sqlSession.selectOne("memberMapper.login", memberVO);
    }
}
