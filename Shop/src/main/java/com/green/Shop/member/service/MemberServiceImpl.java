package com.green.Shop.member.service;

import com.green.Shop.member.vo.MemberVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service ("memberService")
public class MemberServiceImpl implements MemberService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public void insertMember(MemberVO memberVO) {
        sqlSession.insert("memberMapper.insertMember", memberVO);
    }

    @Override
    public MemberVO selectMember(MemberVO memberVO) {
        return sqlSession.selectOne("memberMapper.selectMember", memberVO);
    }
}
