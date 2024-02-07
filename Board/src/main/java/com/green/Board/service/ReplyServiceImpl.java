package com.green.Board.service;

import com.green.Board.vo.ReplyVO;
import jakarta.annotation.Resource;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("replyService")
public class ReplyServiceImpl implements ReplyService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<ReplyVO> selectReply(int boardNum) {
        return sqlSession.selectList("replyMapper.selectReply", boardNum);
    }

    @Override
    public void insertReply(ReplyVO replyVO) {
        sqlSession.insert("replyMapper.insertReply", replyVO);
    }

    @Override
    public void deleteReply(int replyNum) {
        sqlSession.delete("replyMapper.deleteReply", replyNum);
    }
}
