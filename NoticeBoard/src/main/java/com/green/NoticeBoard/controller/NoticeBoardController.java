package com.green.NoticeBoard.controller;

import com.green.NoticeBoard.service.NoticeBoard;
import com.green.NoticeBoard.service.NoticeBoardImpl;
import com.green.NoticeBoard.vo.NoticeBoardVO;
import jakarta.annotation.Resource;
import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.Name;
import java.util.List;

@Controller
public class NoticeBoardController {
    @Resource(name = "NoticeBoard")
    private NoticeBoardImpl noticeBoard;

    @GetMapping("/") //게시판 메인
    public String board_list(Model model){

        List<NoticeBoardVO> list = noticeBoard.boardList();
        model.addAttribute("list", list);
        return "board_list";
    }

    @GetMapping("/go_board_write_form") //게시글 작성
    public String board_write_form(){
        return "board_write_form";
    }

    @PostMapping("/insertNotice") //게시글 등록 후 메인
    public String insertBoard_list(NoticeBoardVO noticeBoardVO){
        noticeBoard.insertNotice(noticeBoardVO);
        return "redirect:/";
    }

    @GetMapping("/boardInfo") //게시글 정보
    public String boardInfo(@RequestParam(name = "boardNum") int boardNum
                            , Model model){
        //조회수
        noticeBoard.readCnt(boardNum);

        NoticeBoardVO boardInfo = noticeBoard.boardInfo(boardNum);
        model.addAttribute("boardInfo", boardInfo);

        return "board_detail";
    }

    @GetMapping("/deleteNotice") //게시글 삭제
    public String deleteNotice(NoticeBoardVO noticeBoardVO){
        noticeBoard.deleteNotice(noticeBoardVO.getBoardNum());
        return "redirect:/";
    }

    @GetMapping("updateNotice") //게시글 수정
    public String updateNotice(@RequestParam(name = "boardNum") int boardNum
                                ,Model model){
        NoticeBoardVO updateInfo = noticeBoard.boardInfo(boardNum);
        model.addAttribute("updateInfo", updateInfo);

        return "update_form";
    }

    @PostMapping("updateList") //게시글 수정 후 게시판가기
    public String updateList(NoticeBoardVO noticeBoardVO, Model model) {
        noticeBoard.updateNotice(noticeBoardVO);
        return "redirect:/boardInfo?boardNum=" + noticeBoardVO.getBoardNum();
    }
}
