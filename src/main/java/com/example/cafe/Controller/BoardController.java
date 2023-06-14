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
    private final UserService userService;

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
        boardService.resBoard(boardDto, user);
        return "redirect:/board";
    }


    @GetMapping("/boardinfo")
    public String boardinfo(@RequestParam("boardIdx") Long boardIdx, HttpServletRequest req, Model model) {
        HttpSession session = req.getSession();
        UserDto dto = (UserDto) session.getAttribute("userinfo");
        var boardinfo = boardService.findBoard(boardIdx);
        model.addAttribute("board", boardinfo);
        System.err.println("boardinfo 컨트롤러에서 테스트입니다 나오는지?" + boardinfo);
        // -> 나옵니다잉.
        System.err.println("boardinfo 컨트롤러에서 테스트입니다 나오는지?" + dto);
        // -> null 인 이유.


        return "boardinfo";
    }
}
