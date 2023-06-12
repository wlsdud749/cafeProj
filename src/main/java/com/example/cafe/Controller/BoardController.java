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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;

    @Transactional
    @PostMapping("/writeBoard")
    public String boardRes(@RequestBody BoardDto boardDto, HttpServletRequest req) {

        HttpSession session = req.getSession();
        UserDto dto = (UserDto) session.getAttribute("userinfo");

        // 작성된 게시물을 DB 에 저장
        var user = dto.getIdx();
        // 찍힘
//        System.out.println("user idx 찍히는지 확인"+ user);
        boardService.resBoard(boardDto,user);

//        HttpSession session = req.getSession();
//        UserDto dto = (UserDto) session.getAttribute("userinfo");
        return "redirect:/board";
    }

    @GetMapping("/board")
    public String boardProc(Model model, HttpServletRequest req) {
        System.out.println("접근하는지 확인");
        // GET 할때마다 불러온다면?
        HttpSession session = req.getSession();
        UserDto dto = (UserDto) session.getAttribute("userinfo");
        List<Board> boardList = boardRepository.findAll(); // 게시글 리스트 가져오기
        // 됐다 나온다.
        model.addAttribute("boardList", boardList); // 모델에 리스트 추가

//        System.out.println("boardList 나오는지 확인 " + boardList);
        return "board";
    }

    @GetMapping("/boardinfo")
    public String boardinfo(@RequestParam("boardIdx") Long boardIdx , HttpServletRequest req, Model model) {
//        System.out.println("접근은 하는지 (boardinfo)");
        HttpSession session = req.getSession();
        UserDto dto = (UserDto) session.getAttribute("userinfo");
        var board = boardRepository.findById(boardIdx);
        model.addAttribute(String.valueOf(board),"boardinfo");
//        System.out.println("boardinfo 나오는지?"+board);


        return "boardinfo";
    }
}
