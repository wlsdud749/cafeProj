package com.example.cafe.service;

import com.example.cafe.dto.CafeResDto;
import com.example.cafe.kakao.KakaoClient;
import com.example.cafe.kakao.dto.LocalSearchReqDto;
import com.example.cafe.kakao.dto.LocalSearchResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class KakaoService {

    private final KakaoClient kakaoClient;

    public LocalSearchResDto findLocalSearch(LocalSearchReqDto reqDto) {
        return kakaoClient.localSearch(reqDto);
    }


    public List<CafeResDto> search(String query) {

        var cafeDtos = new ArrayList<CafeResDto>();

        /*
         * naver local search area
         * */
        var localSearchDto = LocalSearchReqDto.builder().query(query).build();
        var localResult = kakaoClient.localSearch(localSearchDto);

        localResult.getDocuments().stream().limit(10).forEach(item -> {
            var cafeDto = CafeResDto.builder()
                    // 카페 이름
                    .name(item.getPlace_name())
                    // 전화 번호
                    .number(item.getPhone())
                    // 전체 지번
                    .addr(item.getAddress_name())
                    //전체 도로명 주소
                    .addr2(item.getRoad_address_name())
                    // x좌표
                    .xValue(item.getX())
                    // y좌표
                    .yValue(item.getY())
                    .build();
            cafeDtos.add(cafeDto);

        });

        return cafeDtos;
    }
}

// ************ 강사 버전 **************************** //
//        var optItem = localResult.getDocuments().stream().findFirst();
//        // null 값인데? 이유 찾기 ㄱ 여기서 받아와야 하는데, 여기서 정보가 저장되어야 함.
//        // 찾았다. 원인은 get 으로 불러왔을 때, document 밑에 정보들이 있었음. 이것을 맞춰줘야 하는군
//        // 맨 위에 있는 정보만 저장하는디
//
//        if (optItem.isPresent()) {
//            var item = optItem.get();
//
//
//            // 카페 이름
//            cafeDto.setName(item.getPlace_name());
//            // 전화 번호
//            cafeDto.setNumber(item.getPhone());
//            // 전체 지번
//            cafeDto.setAddr(item.getAddress_name());
//            //전체 도로명 주소
//            cafeDto.setAddr2(item.getRoad_address_name());
//            // x좌표
//            cafeDto.setXValue(item.getX());
//            // y좌표
//            cafeDto.setYValue(item.getY());
//
//            log.info("{} search result = ", cafeDto);
//
//        }
//        return cafeDto;
