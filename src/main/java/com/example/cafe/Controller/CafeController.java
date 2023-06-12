package com.example.cafe.Controller;

import com.example.cafe.dto.CafeResDto;
import com.example.cafe.dto.UserDto;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;


@Controller
@Slf4j
@RequiredArgsConstructor
public class CafeController {


    private final KakaoService kakaoService;
    private final CafeService cafeService;
    private final CafeRepository cafeRepository;


    // **********  추가 기능  **********
    @PostMapping(value = "/registercafe")
    public String registerCafe(CafeResDto cafeResDto, HttpServletRequest req) {
//        System.out.println("카페 등록시, 카페 ResDto 의 정보" + cafeResDto.toString());
        // 이게 에러
        HttpSession session = req.getSession();
        UserDto user = (UserDto) session.getAttribute("userinfo");
        var cafe = cafeService.registerCafe(cafeResDto, user);
//        System.out.println("user 정보 :" + user + "cafe 정보:" + cafe);
        return "redirect:/home";
    }

    // ************* cafe 정보 담아서 예약 페이지에 뿌리기 *************

    // 게시글 삭제
    @PostMapping("/delete")
    public String deleteCafe(@RequestParam("deleteForm") Long deleteForm) {
        cafeService.delCafe(deleteForm);
        return "redirect:/home";
    }

    // 검색 결과를 나타내네
    @PostMapping(value = "/searchCafe")
    public String findSearch(@RequestParam("query") String query, Model model) {
        List<CafeResDto> searchResults = kakaoService.search(query);

        model.addAttribute("searchResults", searchResults);
//        System.out.println("정보:----" + searchResults);
        return "searchResult";

    }

    @Transactional
    @PostMapping(value = "/infoCafe")
    public String infoCafe(@RequestParam("infoCafe") Long infoCafe, Model model) {

        var cafeResDto = cafeRepository.findById(infoCafe).get();
        model.addAttribute("infoCafe", cafeResDto);
        System.out.println("cafeResDto 정보 *******" + cafeResDto);
        return "reserve";
    }
}
