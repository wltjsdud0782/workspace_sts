package com.green.BasicBoard.vo;

import lombok.*;

// 생성자를 구현하는 어노테이션
// @NoArgsConstructor // 매개변수가 없는 기본 생성자
// @AllArgsConstructor // 멤버 변수 모두를 매개  수로 받는 생성자
// @RequiredArgsConstructor // final 매개변수(?)
@Data // 기본생성자, setter, getter, toString()
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {
    private String memberId;
    private String memberName;
    private String memberPw;
    private String memberRoll;
}

class BuilderTest{
    public void test(){
        // id를 java라는 초기값으로 갖는 객체 생성
        MemberVO v1 = MemberVO.builder()
                                .memberId("java").build();

        MemberVO v2 = MemberVO.builder()
                                .memberId("java")
                                .memberName("hong")
                                .build();

        MemberVO v3 = MemberVO.builder()
                                .memberName("hong")
                                .memberPw("1111")
                                .memberRoll("USER")
                                .build();

        MemberVO v4 = MemberVO.builder().build();

    }
}
