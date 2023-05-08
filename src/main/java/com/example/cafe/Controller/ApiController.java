package com.example.cafe.Controller;


import com.example.cafe.dto.CafeResDto;
import com.example.cafe.dto.RoomDto;
import com.example.cafe.dto.UserDto;
import com.example.cafe.entity.Cafe;
import com.example.cafe.entity.User;
import com.example.cafe.kakao.KakaoClient;
import com.example.cafe.kakao.dto.LocalSearchReqDto;
import com.example.cafe.repository.CafeRepository;
import com.example.cafe.repository.UserRepository;
import com.example.cafe.service.CafeService;
import com.example.cafe.service.KakaoService;
import com.example.cafe.service.RoomService;
import com.example.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequestMapping(value = "/apis")
@RequiredArgsConstructor
@RestController
public class ApiController {

    private final KakaoService kakaoService;
    private final UserService userService;
    private final CafeService cafeService;
    private final RoomService roomService;
    private final UserRepository userRepository;
    private final CafeRepository cafeRepository;
//    private final CafeService cafeService;


    @GetMapping(value = "/search/local")
    public ResponseEntity findLocalSearch(@RequestParam("query") String query) {
        var reqDto = LocalSearchReqDto.builder().query(query).build();
        return ResponseEntity.status(HttpStatus.OK).body(kakaoService.findLocalSearch(reqDto));
    }

    @GetMapping(value = "/search")
    public ResponseEntity findSearch(@RequestParam("query") String query) {
        return ResponseEntity.status(HttpStatus.OK).body(kakaoService.search(query));
    }

    @GetMapping("/reserveUser")
    public String reserveUser(@RequestParam("seat_id") String seat_id, @RequestParam("cafe_idx") Long cafeIdx , HttpSession session, HttpServletResponse response, Model model) {

        // 세션에 저장된 값 가져오기.
        UserDto userDto = (UserDto) session.getAttribute("userinfo");
        System.out.println("넘어오는지 확인용 **** seat_id = " + seat_id);
        // cafeIdx 받아오는지?
        System.out.println("넘어오는지 확인용 **** cafeIdx = " + cafeIdx);
        // user name 도 받아 옴
        System.out.println("넘어오는지 확인용 **** user 모든 정보 = " + userDto);
        // 컨트롤러는 정보 전달 주고받고만 하자 - 복쌤
        var cafeInfo = cafeService.getCafeInfo(cafeIdx, seat_id, userDto);
        List<RoomDto> roomDto = cafeService.getRoom(cafeIdx);
        System.out.println("넘어오는지 확인용 **** cafe 모든 정보 ="+roomDto.toString());
        // -> null 값인데
        // 값을 받아오려면 idx 를 가져와야 하는데 아는게 없네.
        //        System.out.println("넘어오는지 확인용 **** cafe = " + cafe);
        model.addAttribute("roomDto", roomDto);
        return "redirect:/";
    }

    // 등록은 나중에 하고,
//    @PostMapping(value="/cafeRegister")
//    public ResponseEntity registerCafe (@RequestBody CafeResDto cafeResDto) {
//        log.info("save to cafe = {}",cafeResDto);
//        var dto = cafeService.registerCafe(cafeResDto);
//        if(dto.getIdx() != null) {
//            return ResponseEntity.ok(dto);
//        }
//        return ResponseEntity.status(HttpStatus.CONFLICT).body(dto);
//    }

}
