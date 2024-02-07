package com.green.Board.service;

import com.green.Board.vo.BoardVO;
import com.green.Board.vo.SearchVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import javax.xml.transform.Result;
import java.util.List;
@Service ("boardService")
public class BoardServiceImpl implements BoardService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override //목록조회
    public List<BoardVO> selectBoard(SearchVO searchVO) {
        return sqlSession.selectList("boardMapper.selectBoard", searchVO);
    }

    @Override //글 추가
    public void insertBoard(BoardVO boardVO) {
        sqlSession.insert("boardMapper.insertBoard", boardVO);
    }

    @Override //글 정보
    public BoardVO selectBoardDetail(int boardNum) {
        return sqlSession.selectOne("boardMapper.selectBoardDetail", boardNum);
    }

    @Override //글 수정
    public void updateBoard(BoardVO boardVO) {
        sqlSession.update("boardMapper.updateBoard", boardVO);
    }

    @Override //글 삭제
    public void deleteBoard(int boardNum) {
        sqlSession.delete("boardMapper.deleteBoard", boardNum);
    }

    @Override //조회수
    public void readCnt(int boardNum) {
        sqlSession.update("boardMapper.readCnt", boardNum);
    }

    @Override
    public int selectBoardCnt() {
        return sqlSession.selectOne("boardMapper.selectBoardCnt");
    }

    // 글 검색


}
