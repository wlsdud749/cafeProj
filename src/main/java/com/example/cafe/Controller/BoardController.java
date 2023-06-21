package com.example.cafe.Controller;


import com.example.cafe.dto.BoardDto;
import com.example.cafe.dto.UserDto;
import com.example.cafe.entity.Board;
import com.example.cafe.repository.BoardRepository;
import com.example.cafe.repository.CafeRepository;
import com.example.cafe.service.BoardService;
import com.example.cafe.service.CafeService;
import com.example.cafe.service.KakaoService;
import com.example.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;

    // boardList 도 출력하는 코드도 같이 있음.
    @GetMapping("/board")
    public String boardProc(Model model, HttpServletRequest req) {
        HttpSession session = req.getSession();
        UserDto dto = (UserDto) session.getAttribute("userinfo");
        List<Board> boardList = boardRepository.findAll(); // 게시글 리스트 가져오기
        model.addAttribute("boardList", boardList); // 모델에 리스트 추가
        return "board";
    }

    // 게시글 작성할 때,
    @Transactional
    @PostMapping("/writeBoard")
    public String boardRes(@RequestBody BoardDto boardDto, HttpServletRequest req) {

        HttpSession session = req.getSession();
        UserDto dto = (UserDto) session.getAttribute("userinfo");

        // 작성된 게시물을 DB 에 저장
        var user = dto.getIdx();
        if (user == null) {
            return "redirect:/board";
        }
        boardService.resBoard(boardDto, user);
        return "redirect:/board";
    }


    // 게시글 정보
    @GetMapping("/boardinfo")
    public String boardinfo(@RequestParam("boardIdx") Long boardIdx, HttpServletRequest req, Model model) {

        HttpSession session = req.getSession();
        UserDto dto = (UserDto) session.getAttribute("userinfo");

        var boardinfo = boardService.findBoard(boardIdx);
        model.addAttribute("board", boardinfo);
//        System.err.println(boardinfo.getIdx());


        return "boardinfo";
    }

    // 게시글 삭제
    @Transactional
    @PostMapping("/delBoard")
    public String delBoard(@RequestParam("boardIdx") Long boardIdx) {

        boardService.delBoard(boardIdx);

        return "redirect:/board";

    }

    // 게시글 수정
    @GetMapping("boardEdit")
    public String editBoard(@RequestParam("boardIdx") Long boardIdx, Model model) {

        Board board = boardService.findBoard(boardIdx);
        model.addAttribute("board", board);
//        System.err.println(board);

        return "boardEdit";

    }

    @PostMapping("updateBoard")
    public String updateBoard(@RequestParam("boardIdx") Long boardIdx, @RequestParam("title") String title, @RequestParam("content") String content) {

        System.err.println(boardIdx + " " + title + " " + content + " ");
//        -> 넘어옴
//        Board board = boardRepository.findById(boardIdx).get();


        Board board = boardService.updateBoard(boardIdx,title,content);

//        System.err.println("수정됐는지 확인" + board);


        return "redirect:/board";

    }
}
