package com.example.cafe.service;


import com.example.cafe.dto.CafeResDto;
import com.example.cafe.entity.Cafe;
import com.example.cafe.repository.CafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CafeService {
    private final CafeRepository cafeRepository;

    public CafeResDto registerCafe(CafeResDto cafeResDto) {
        //Cafe cafe = null;
        //if(cafeResDto.getIdx() == null) {

        var cafe = Cafe.builder()
                .name(cafeResDto.getName())
                .addr(cafeResDto.getAddr())
                .addr2(cafeResDto.getAddr2())
                .number(cafeResDto.getNumber())
                .x(cafeResDto.getXValue())
                .y(cafeResDto.getYValue())
                .build();

        // 업데이트 로직이라고 함.
//            var entity = cafeRepository.findById(cafeResDto.getIdx()).get();
//            entity.setIdx(cafeResDto.getIdx(cafeResDto.getName()));
//            entity.setName(cafeResDto.getName());
//            entity.setAddr(cafeResDto.getAddr());
//            entity.setAddr2(cafeResDto.getAddr2());
//            entity.setNumber(cafeResDto.getNumber());

            cafe = cafeRepository.save(cafe);
        //}

        return entityToDto(cafe);
    }

    // 프론트에 던저주기 위해서 entityToDto 를 사용
    private CafeResDto entityToDto(Cafe cafe) {
        var dto = CafeResDto.builder().
                name(cafe.getName())
                .idx(cafe.getIdx())
                .addr(cafe.getAddr())
                .addr2(cafe.getAddr2())
                .number(cafe.getNumber())
                .build();
        return dto;
    }

    private Cafe dtoToEntity(CafeResDto cafeResDtoDto) {
        var dto = Cafe.builder()
                .name(cafeResDtoDto.getName())
                .addr(cafeResDtoDto.getAddr())
                .addr2(cafeResDtoDto.getAddr2())
                .number(cafeResDtoDto.getNumber())
                .build();
        return dto;
    }

    public Cafe saveCafe(Cafe cafe) {
        return cafeRepository.save(cafe);
    }
}
