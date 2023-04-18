package com.example.cafe.Controller;


import com.example.cafe.dto.CafeResDto;
import com.example.cafe.kakao.KakaoClient;
import com.example.cafe.kakao.dto.LocalSearchReqDto;
import com.example.cafe.repository.CafeRepository;
import com.example.cafe.service.CafeService;
import com.example.cafe.service.KakaoService;
import com.example.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequestMapping(value = "/apis")
@RequiredArgsConstructor
@RestController
public class ApiController {

    private final KakaoService kakaoService;
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
