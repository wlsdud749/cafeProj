package com.example.cafe.Controller;


import com.example.cafe.dto.UserDto;
import com.example.cafe.entity.Cafe;
import com.example.cafe.repository.BoardRepository;
import com.example.cafe.repository.CafeRepository;
import com.example.cafe.service.BoardService;
import com.example.cafe.service.CafeService;
import com.example.cafe.service.KakaoService;
import com.example.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 로그인
    @PostMapping(value = "/signin")
    // redirect 시 RedirectAttributes 클래스를 사용하여 전달할 수 있다.
    public String signIn(UserDto userDto, RedirectAttributes redirectAttributes, HttpServletRequest req, Model model) {
//        log.info("이거 어디고 #*#*#**#*#*#*#*#**#**#* user id = {}, user pw = {}", userDto.getId(), userDto.getPasswd());
        // ID 랑 PW 받아오기는 함.
        // 입력한 정보.

        var user = userService.findByIdAndPasswd(userDto);
        if (user == null) {
//            System.out.println("user 가 널 값 입니다.");
            redirectAttributes.addFlashAttribute("status", HttpStatus.BAD_REQUEST.toString());
            return "redirect:/error";
        }

        // session 을 통해서 http 가 작동 할 때, 계속해서 정보를 담고 있는 session 을 만든다?
        HttpSession session = req.getSession();
        session.setAttribute("userinfo", user);
        model.addAttribute("userinfo", user);
        System.out.println("userinfo 확인하기 (로그인 했을 경우) : ****" + user);
        return "redirect:/home";
    }

    @GetMapping(value = "/home")
    public void home(HttpServletRequest req, Model model) {
//        유저정보
        // user 정보 저장.
        HttpSession session = req.getSession();
        UserDto dto = (UserDto) session.getAttribute("userinfo");
//        idx 별 유저의 카페 정보
        Optional<List<Cafe>> CafeDto = userService.getList(dto.getIdx());
//        System.out.println(CafeDto.toString());
        model.addAttribute("cafeinfo", CafeDto.orElse(null));

        // test
//        var test = userService.getUser(dto.getIdx());
//        System.out.println("test 용입니다 +++"+test);
        // user_idx 와 user 테이블의 idx 가 동일한 cafe 테이블 출력
//        System.out.println(CafeDto.getClass());


    }

    @PostMapping(value = "/signup")
    public String signUp(UserDto userDto, RedirectAttributes redirectAttributes) {
//        System.out.println(userDto.toString());
        var user = userService.registerUser(userDto);

        redirectAttributes.addFlashAttribute("userinfo", user);
        return "redirect:/login";
    }
}
