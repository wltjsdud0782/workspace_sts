package com.green.NoticeBoard.service;

import com.green.NoticeBoard.vo.NoticeBoardVO;

import java.util.List;

public interface NoticeBoard {
    List<NoticeBoardVO> boardList(); //목록

    NoticeBoardVO boardInfo(int boardNum); //상세보기

    void insertNotice(NoticeBoardVO noticeBoardVO); //추가

    void deleteNotice(int boardNum); //삭제

    int updateNotice(NoticeBoardVO noticeBoardVO); //수정

    int readCnt(int boardNum); //조회수

}
