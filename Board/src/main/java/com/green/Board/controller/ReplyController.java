package com.green.Board.controller;

import com.green.Board.service.ReplyServiceImpl;
import com.green.Board.vo.MemberVO;
import com.green.Board.vo.ReplyVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reply")
public class ReplyController {
    @Resource (name = "replyService")
    private ReplyServiceImpl replyService;

    @PostMapping("/insertReply") //댓글 등록
    public String insertReply(HttpSession session, ReplyVO replyVO){
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo"); //아이디 조회
        replyVO.setWriter(loginInfo.getMemberId()); //vo 비었던 작성자 값 넣기
        replyService.insertReply(replyVO); //insert
        return "redirect:/board/boardDetail?boardNum="+replyVO.getBoardNum(); //boardNum 변수 넘겨주기
    }

    @PostMapping("deleteReply") //댓글 삭제
    public String deleteReply(@RequestParam (name = "replyNum") int replyNum, ReplyVO replyVO, Model model){
        replyService.deleteReply(replyNum);
        return "redirect:/board/boardDetail?boardNum="+replyVO.getBoardNum();
    }
}
