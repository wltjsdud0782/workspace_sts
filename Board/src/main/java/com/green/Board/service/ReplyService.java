package com.green.Board.service;

import com.green.Board.vo.BoardVO;
import com.green.Board.vo.ReplyVO;

import java.util.List;

public interface ReplyService {
    List<ReplyVO> selectReply(int boardNum); //댓글 조회

    void insertReply(ReplyVO replyVO); //댓글 추가

//    void insertReply(BoardVO boardVO); 받는 값이 다 포함 되어 있어서 BoardVO 도 가능.

    void deleteReply(int replyNum); //댓글 삭제
}
