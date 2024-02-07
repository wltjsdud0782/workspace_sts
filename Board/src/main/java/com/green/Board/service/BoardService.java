package com.green.Board.service;

import com.green.Board.vo.BoardVO;
import com.green.Board.vo.SearchVO;

import javax.swing.*;
import java.util.List;

public interface BoardService {
    List<BoardVO> selectBoard(SearchVO searchVO); //목록 조회

    void insertBoard(BoardVO boardVO); //글 추가

    BoardVO selectBoardDetail(int boardNum); //글 상세 정보

    void updateBoard(BoardVO boardVO); //글 수정

    void deleteBoard(int boardNum); //글 삭제

    void readCnt(int boardNum); //조회수

    int selectBoardCnt(); //전체 데이터(게시글) 갯수
}
