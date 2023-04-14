package com.example.cafe.service;


import com.example.cafe.dto.CafeResDto;
import com.example.cafe.dto.UserDto;
import com.example.cafe.entity.Cafe;
import com.example.cafe.entity.User;
import com.example.cafe.repository.CafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CafeService {
    private final CafeRepository cafeRepository;
    private final UserService userService;

    public CafeResDto registerCafe(CafeResDto cafeResDto) {
        //Cafe cafe = null;
        //if(cafeResDto.getIdx() == null) {

//        // ***********
//        User user = userService.dtoToEntity(userDto); // UserDto를 User 엔티티로 변환
//        Cafe cafe2 = new Cafe();
//        cafe2.setUser(user); // User 엔티티를 Cafe 엔티티에 설정
//        cafeRepository.save(cafe2);
//        System.out.println("userDto idx 확인용 -----"+userDto.getIdx());
//        // ***********


        var cafe = Cafe.builder()
                .name(cafeResDto.getName())
                .addr(cafeResDto.getAddr())
                .addr2(cafeResDto.getAddr2())
                .number(cafeResDto.getNumber())
                .x(cafeResDto.getXValue())
                .y(cafeResDto.getYValue())
                .build();

//        cafe.setUserIdx(userDto.getIdx());

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

    public Cafe saveCafe(CafeResDto caferesDto, UserDto userDto) {
        User user = userService.dtoToEntity(userDto); // UserDto를 User 엔티티로 변환
        Cafe cafe = new Cafe();
        return cafeRepository.save(cafe);
    }
}
