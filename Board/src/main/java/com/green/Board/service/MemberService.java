package com.green.Board.service;

import com.green.Board.vo.MemberVO;

public interface MemberService {

    void insertMember(MemberVO memberVO); //회원 등록

    MemberVO login(MemberVO memberVO); //로그인
}
