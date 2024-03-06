package com.green.BasicBoard.service;

import com.green.BasicBoard.vo.BoardVO;
import com.green.BasicBoard.vo.MemberVO;

import java.util.List;

public interface BoardService {

    //게시글 목록 조회
    List<BoardVO> selectBoardList();

    //게시글 등록
    void insertBoard(BoardVO boardVO);

    //게시글 상세 조회
    BoardVO selectBoardDetail(int boardNum);

    //게시글 삭제
    void deleteBoard(int boardNum);

    //게시글 정보 수정
    void updateBoard(BoardVO boardVO);

    // 회원가입
    void join(MemberVO memberVO);

    // 로그인
    MemberVO login(MemberVO memberVO);

}
