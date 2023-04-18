package com.example.cafe.Controller;

import com.example.cafe.dto.CafeResDto;
import com.example.cafe.dto.UserDto;
import com.example.cafe.entity.Cafe;
import com.example.cafe.repository.CafeRepository;
import com.example.cafe.repository.UserRepository;
import com.example.cafe.service.CafeService;
import com.example.cafe.service.KakaoService;
import com.example.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ViewsController {

    private final UserService userService;
    @Autowired
    private final KakaoService kakaoService;
    private final CafeService cafeService;
    private final UserRepository userRepository;
    private final CafeRepository cafeRepository;

    @GetMapping(value = "/login")
    public void loginProc() {

    }

    // 로그인
    @PostMapping(value = "/signin")
    // redirect 시 RedirectAttributes 클래스를 사용하여 전달할 수 있다.
    public String signIn(UserDto userDto, RedirectAttributes redirectAttributes, HttpServletRequest req,Model model) {
        log.info("이거 어디고 #*#*#**#*#*#*#*#**#**#* user id = {}, user pw = {}", userDto.getId(), userDto.getPasswd());
        // ID 랑 PW 받아오기는 함.
        // 입력한 정보.

        var user = userService.findByIdAndPasswd(userDto);
        if (user == null) {
            System.out.println("user 가 널 값 입니다.");
            redirectAttributes.addFlashAttribute("status",
                    HttpStatus.BAD_REQUEST.toString());
            return "redirect:/error";
        }

        // session 을 통해서 http 가 작동 할 때, 계속해서 정보를 담고 있는 session 을 만든다?
        HttpSession session = req.getSession();
        session.setAttribute("userinfo", user);
        model.addAttribute("userinfo",user);
        System.out.println("userinfo 확인하기 (로그인 했을 경우) : ****" + user);
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
//        유저정보
        // user 정보 저장.
        HttpSession session = req.getSession();
        UserDto dto = (UserDto) session.getAttribute("userinfo");
//        idx 별 유저의 카페 정보
        Optional<List<Cafe>> CafeDto = userService.getList(dto.getIdx());
//        System.out.println(CafeDto.toString());
        model.addAttribute("cafeinfo", CafeDto.orElse(null));
        // user_idx 와 user 테이블의 idx 가 동일한 cafe 테이블 출력
//        System.out.println(CafeDto.getClass());


    }
    // **********  추가 기능  **********
    @PostMapping(value="/registercafe")
    public String registerCafe (CafeResDto cafeResDto,HttpServletRequest req) {
//        System.out.println("카페 등록시, 카페 ResDto 의 정보" + cafeResDto.toString());
        // 이게 에러
        // 정보가 안찍히노
        HttpSession session = req.getSession();
        UserDto user = (UserDto)session.getAttribute("userinfo");
        var cafe = cafeService.registerCafe(cafeResDto, user);
        System.out.println("user 정보 :"+user + "cafe 정보:"+cafe);
        return "redirect:/home";
    }

    // ************* cafe 정보 담아서 예약 페이지에 뿌리기 *************

    // 게시글 삭제
    @PostMapping("/delete")
    public String deleteCafe (@RequestParam ("deleteForm") Long deleteForm) {
        cafeService.delCafe(deleteForm);
        return "redirect:/home";
    }

    @GetMapping(value = "/user-add")
    public void userAddProc() {

    }



    @PostMapping(value = "/signup")
    public String signUp(UserDto userDto, RedirectAttributes redirectAttributes) {
        System.out.println(userDto.toString());
        var user = userService.registerUser(userDto);

        redirectAttributes.addFlashAttribute("userinfo",
                user);
        return "redirect:/login";
    }

    // 검색 결과를 나타내네
    @PostMapping(value="/searchCafe")
    public String findSearch(@RequestParam("query") String query,Model model) {
        List<CafeResDto> searchResults =kakaoService.search(query);

        model.addAttribute("searchResults",searchResults);
        System.out.println("정보:----" + searchResults);
        return "searchResult";

    }

    @Transactional
    @PostMapping(value = "/infoCafe")
    public String infoCafe(@RequestParam("infoCafe") Long infoCafe,Model model ) {
        var cafeResDto = cafeRepository.findById(infoCafe).get();
        model.addAttribute("infoCafe",cafeResDto);
        System.out.println("cafeResDto 정보 *******"+cafeResDto);


        return "reserve";
    }
    @GetMapping(value = "/reserve")
    public void reserveProc() {

    }
    @GetMapping(value = "/map")
    public void mapProc() {

    }

    @GetMapping(value = "/search2")
    public void searchProc() {

    }
}

