package com.green.NoticeBoard.service;

import com.green.NoticeBoard.vo.NoticeBoardVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("NoticeBoard")
public class NoticeBoardImpl implements NoticeBoard{
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override //메인
    public List<NoticeBoardVO> boardList() {
        return sqlSession.selectList("boardList");
    }

    @Override //게시글 정보
    public NoticeBoardVO boardInfo(int boardNum) {
        return sqlSession.selectOne("boardInfo", boardNum);
    }

    @Override //추가
    public void insertNotice(NoticeBoardVO noticeBoardVO) {
        sqlSession.insert("insertNotice", noticeBoardVO);
    }

    @Override //삭제
    public void deleteNotice(int boardNum) {
        sqlSession.delete("deleteNotice", boardNum);
    }

    @Override //수정
    public int updateNotice(NoticeBoardVO noticeBoardVO) {
        return sqlSession.update("updateNotice", noticeBoardVO);
    }

    @Override //조회수
    public int readCnt(int boardNum) {
        return sqlSession.update("readCnt", boardNum);
    }


}
