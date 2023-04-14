package com.example.cafe.Controller;

import com.example.cafe.dto.CafeResDto;
import com.example.cafe.dto.UserDto;
import com.example.cafe.entity.Cafe;
import com.example.cafe.service.CafeService;
import com.example.cafe.service.KakaoService;
import com.example.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ViewsController {

    private final UserService userService;
    private final KakaoService kakaoService;
    private final CafeService cafeService;

    @GetMapping(value = "/login")
    public void loginProc() {

    }

    // 로그인
    @PostMapping(value = "/signin")
    // redirect 시 RedirectAttributes 클래스를 사용하여 전달할 수 있다.
    public String signIn(UserDto userDto, RedirectAttributes redirectAttributes) {
        log.info("user id = {}, user pw = {}", userDto.getId(), userDto.getPasswd());
        // ID 랑 PW 받아오기는 함.
        // 입력한 정보.

        var user = userService.findByIdAndPasswd(userDto);
        if (user == null) {
            System.out.println("user 가 널 값 입니다.");
            redirectAttributes.addFlashAttribute("status",
                    HttpStatus.BAD_REQUEST.toString());
            return "redirect:/error";
        }

        redirectAttributes.addFlashAttribute("userinfo",
                user);
        return "redirect:/home";
    }

//    @GetMapping(value="/home")
//    public void home(HttpServletRequest req, Model model) {
//        var user = RequestContextUtils.getInputFlashMap(req);
//        UserDto dto = (UserDto) user.get("userinfo");
//        Optional<User> UserDto = userService.getList(dto.getIdx());
//        model.addAttribute("userinfo", UserDto.orElse(null));
//        System.out.println("저장됨?"+UserDto);
//    }

    @GetMapping(value = "/home")
    public void home(HttpServletRequest req, Model model) {
        var map = RequestContextUtils.getInputFlashMap(req);
        UserDto dto = (UserDto) map.get("userinfo");
        // user 정보 저장.
        Optional<List<Cafe>> CafeDto = userService.getList(dto.getIdx());
        System.out.println(CafeDto.toString());
        model.addAttribute("cafeinfo", CafeDto.orElse(null));
        // user_idx 와 user 테이블의 idx 가 동일한 cafe 테이블 출력
//        System.out.println(CafeDto.getClass());

    }

    @GetMapping(value = "/user-add")
    public void userAddProc() {

    }
    @PostMapping(value="/registercafe")
    public String registerCafe (CafeResDto cafeResDto, RedirectAttributes redirectAttributes) {
        System.out.println(cafeResDto.toString());
        var cafe = cafeService.registerCafe(cafeResDto);
        return "redirect:/searchResult";
    }

    @PostMapping(value = "/signup")
    public String signUp(UserDto userDto, RedirectAttributes redirectAttributes) {
        System.out.println(userDto.toString());
        var user = userService.registerUser(userDto);

        redirectAttributes.addFlashAttribute("userinfo",
                user);
        return "redirect:/login";
    }

    @PostMapping(value="/searchCafe")
    public String findSerach(@RequestParam("query") String query,Model model) {
        List<CafeResDto> searchResults =kakaoService.search(query);
        model.addAttribute("searchResults",searchResults);
        System.out.println("정보:----" + searchResults);
        return "searchResult";

    }


    @GetMapping(value = "/map")
    public void mapProc() {

    }

    @GetMapping(value = "/search2")
    public void searchProc() {

    }
}

