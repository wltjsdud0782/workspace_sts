package com.green.BasicBoard.controller;

import com.green.BasicBoard.service.BoardServiceImpl;
import com.green.BasicBoard.vo.BoardVO;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BoardController {
    @Resource(name = "boardService")
    private BoardServiceImpl boardService;
    @Autowired
    private BCryptPasswordEncoder encoder;

    //게시글 목록 페이지로 이동
    @GetMapping("/")
    public String boardList(Model model){
        //목록 데이터 조회 후 HTML 전달
        List<BoardVO> boardList = boardService.selectBoardList();
        model.addAttribute("boardList", boardList);

        // 암호화 예제
        // encode : 매개변수로 전달된 문자열을 암호화
        String s1 = encoder.encode("java");
        String s2 = encoder.encode("java");
        System.out.println(s1);
        System.out.println(s2);

        // 암호화 데이터를 기준 데이터
        boolean b1 = encoder.matches("java", s1); //true 출력
        System.out.println(b1);


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

    @GetMapping("/manager")
    public String manager(){
        return "/manager";
    }

    @GetMapping("/admin")
    public String admin(){
        return "/admin";
    }

    @GetMapping("/deny")
    public String deny(){
        return "/deny";
    }

    @GetMapping("/sample")
    public String sample(){
        return "/security_sample";
    }

    // 로그인 정보 받아오기
    @GetMapping("/sec")
    public String sec(Authentication authentication){
        // 1. 로그인 정보를 받아오기 위해서 매개변수에 Authentication 자료형 추가
        // 2. 로그인 정보 받아오기
        User user = (User)authentication.getPrincipal();

        // 로그인 한 회원의 아이디
        System.out.println(user.getUsername());
        // 로그인 한 회원의 비밀번호
        System.out.println(user.getPassword());
        // 로그인 한 회원의 권한정보
        List<GrantedAuthority> authList = new ArrayList<>(user.getAuthorities());
        for (GrantedAuthority authority : authList) {
            System.out.println(authority.getAuthority());
        }

        return "redirect:/";
    }

}
