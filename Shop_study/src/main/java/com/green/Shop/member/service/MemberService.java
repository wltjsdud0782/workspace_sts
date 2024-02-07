package com.green.Shop.member.service;

import com.green.Shop.member.vo.MemberVO;

public interface MemberService {

    void insertMember (MemberVO memberVO);

    MemberVO selectMember (MemberVO memberVO);
}
