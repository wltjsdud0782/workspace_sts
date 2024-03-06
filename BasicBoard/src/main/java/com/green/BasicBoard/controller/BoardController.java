package com.green.BasicBoard.controller;

import com.green.BasicBoard.service.BoardServiceImpl;
import com.green.BasicBoard.vo.BoardVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BoardController {
    @Resource(name = "boardService")
    private BoardServiceImpl boardService;

    //게시글 목록 페이지로 이동
    @GetMapping("/")
    public String boardList(Model model){
        //목록 데이터 조회 후 HTML 전달
        List<BoardVO> boardList = boardService.selectBoardList();
        model.addAttribute("boardList", boardList);
        return "board_list";
    }

    //글쓰기 페이지로 이동
    @GetMapping("/boardWriteForm")
    public String boardWriteFrom(){
        return "board_write_form";
    }

    //글 등록
    @PostMapping("/boardWrite")
    public String boardWrite(BoardVO boardVO){
        //게시글 insert
        boardService.insertBoard(boardVO);

        return "redirect:/";
    }

    //게시글 상세보기 페이지로 이동
    @GetMapping("/boardDetail")
    public String boardDetail(BoardVO boardVO, Model model){
        //게시글 상세 정보 조회 + html로 전달
        BoardVO board = boardService.selectBoardDetail(boardVO.getBoardNum());
        model.addAttribute("board", board);
        return "board_detail";
    }

    @GetMapping("/deleteBoard")
    public String deleteBoard(BoardVO boardVO){
        //게시글 삭제
        boardService.deleteBoard(boardVO.getBoardNum());

        return "redirect:/";
    }

    //수정 페이지로 이동
    @GetMapping("/updateBoardForm")
    public String updateBoardForm(BoardVO boardVO, Model model){
        //수정하고자 하는 게시글의 데이터를 조회 + html 전달
        BoardVO board = boardService.selectBoardDetail(boardVO.getBoardNum());
        model.addAttribute("board", board);
        return "update_form";
    }

    //게시글 수정
    @PostMapping("/updateBoard")
    public String updateBoard(BoardVO boardVO){
        //게시글 수정
        boardService.updateBoard(boardVO);
        return "redirect:/boardDetail?boardNum=" + boardVO.getBoardNum();
    }

}
