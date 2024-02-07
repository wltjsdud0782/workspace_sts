package com.green.Board.controller;

import com.green.Board.service.BoardServiceImpl;
import com.green.Board.service.MemberService;
import com.green.Board.service.ReplyServiceImpl;
import com.green.Board.vo.BoardVO;
import com.green.Board.vo.MemberVO;
import com.green.Board.vo.PageVO;
import com.green.Board.vo.SearchVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.Name;
import java.lang.reflect.Member;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Resource(name = "boardService")
    private BoardServiceImpl boardService;
    @Resource(name = "replyService")
    private ReplyServiceImpl replyService;

    @RequestMapping("/boardList") //게시글 목록 화면
            // > get 타입, post 타입 둘 다 가능한 request 작성
    public String boardList(SearchVO searchVO, Model model){
        //목록 조회
        model.addAttribute("boardList", boardService.selectBoard(searchVO));
        //List<BoardVO> boardList = boardService.selectBoard(); 에서 뒷 부분을 바로 넣었음 ↑

        //전체 데이터 수 세팅
        int totalDataCnt = boardService.selectBoardCnt();
        //searchVO에 있는 pageVO 의 setter 로 전체 갯수 값 저장
        searchVO.setTotalDataCnt(totalDataCnt);

        //페이지 설정(searchVO가 상속받은 pageVO 사용 가능)
        searchVO.setPageInfo();

        return "board_list";
    }

    @GetMapping("/boardWrite") //글쓰기 화면
    public String boardWrite(){
        return "board_write";
    }

    @PostMapping("/boardWriteSuccess") //글쓰기
    public String boardWriteSuccess(BoardVO boardVO, HttpSession session){
        //로그인한 유저의 아이디로 조회
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");

        //작성자 데이터 저장
        boardVO.setWriter(loginInfo.getMemberId());
        boardService.insertBoard(boardVO);
        return "redirect:/board/boardList";
    }

    @GetMapping("/boardDetail") //글 상세 조회
    public String boardDetail(@RequestParam (name = "boardNum") int boardNum
                            , Model model){

        boardService.readCnt(boardNum); //조회수

        model.addAttribute("board", boardService.selectBoardDetail(boardNum)); //게시글

        model.addAttribute("replyList", replyService.selectReply(boardNum)); //댓글
        return "board_detail";
    }

    @GetMapping("/updateBoard") //글 수정 화면
    public String updateBoard(@RequestParam (name = "boardNum")int boardNum
                                , Model model){
        model.addAttribute("board", boardService.selectBoardDetail(boardNum));
        return "update_board";

    }

    @PostMapping("/updateBoardSuccess") //글 수정 후 상세 정보
    public String updateBoardSuccess(BoardVO boardVO, Model model){
        boardService.updateBoard(boardVO);
        return "redirect:/board/boardDetail?boardNum="+boardVO.getBoardNum();
    }

   @GetMapping("/deleteBoard") //글 삭제
    public String deleteBoard(@RequestParam (name = "boardNum")int boardNum){
        boardService.deleteBoard(boardNum);
        return "redirect:/board/boardList";
   }

}
