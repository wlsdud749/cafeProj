package com.example.cafe.service;


import com.example.cafe.dto.CafeResDto;
import com.example.cafe.dto.UserDto;
import com.example.cafe.entity.Cafe;
import com.example.cafe.repository.CafeRepository;
import com.example.cafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CafeService {

    @Autowired
    private final CafeRepository cafeRepository;
    private final UserService userService;
    private final UserRepository userRepository;

//    public CafeResDto registerCafe(CafeResDto cafeResDto) {
//
//        var cafe = Cafe.builder()
//                .name(cafeResDto.getName())
//                .addr(cafeResDto.getAddr())
//                .addr2(cafeResDto.getAddr2())
//                .number(cafeResDto.getNumber())
//                .x(cafeResDto.getXValue())
//                .y(cafeResDto.getYValue())
//                .build();
//
//
//            cafe = cafeRepository.save(cafe);
//        //}
//
//        return entityToDto(cafe);
//    }
    @Transactional
    public CafeResDto registerCafe(CafeResDto cafeResDto, UserDto user) {
        // CafeResDto에서 필요한 정보 추출
        String name = cafeResDto.getName();
        String addr = cafeResDto.getAddr();
        String addr2 = cafeResDto.getAddr2();
        String number = cafeResDto.getNumber();
        String x = cafeResDto.getXValue();
        String y = cafeResDto.getYValue();
        var userEntity = userRepository.findById(user.getIdx()).get();
        //Long user_idx = user.getIdx();

        System.out.println("user 정보 확인 ----- ******\n "+user);
        // 정보는 잘 전달 됨
         System.out.println("정보 확인용 ------- \n"+cafeResDto);
        // idx 가 나오긴하는데
        // user 정보 확인 -----
        // UserDto(idx=1, id=Bok2, passwd=1234, name=null, nick=복선생2, addr=복 선생 마음 속2, owner=0)
        System.out.println("user_idx 정보 확인 ------ *****"+user.getIdx());
//        System.out.println("user_idx 정보 확인 ----" + user_idx);




        // Cafe 엔티티 생성
        Cafe cafe = Cafe.builder()
                .name(name)
                .addr(addr)
                .addr2(addr2)
                .number(number)
                .x(x)
                .y(y)
                // user_idx 가 1로 찍히는데 , 왜 저장을 못할까?
                .user(userEntity)
                .build();
        // Cafe 엔티티 저장
        cafe = cafeRepository.save(cafe);
        System.out.println("정보 확인용 (Cafe Repository) -->" + cafe);
        // CafeRestDto 의 idx 값은 널이 당연하다고 함.
        // idx 가 널 값.
        // CafeResDto로 변환하여 반환
        return entityToDto(cafe,user);
    }



    // 프론트에 던저주기 위해서 entityToDto 를 사용
    private CafeResDto entityToDto(Cafe cafe, UserDto user) {
        var dto = CafeResDto.builder().
                name(cafe.getName())
                .idx(cafe.getIdx())
                .addr(cafe.getAddr())
                .addr2(cafe.getAddr2())
                .xValue(cafe.getX())
                .yValue(cafe.getY())
                .number(cafe.getNumber())
                .user_idx(user.getIdx())
                .build();
        return dto;
    }

    private Cafe dtoToEntity(CafeResDto cafeResDtoDto) {
        var dto = Cafe.builder()
                .idx(cafeResDtoDto.getIdx())
                .name(cafeResDtoDto.getName())
                .addr(cafeResDtoDto.getAddr())
                .addr2(cafeResDtoDto.getAddr2())
                .number(cafeResDtoDto.getNumber())
                .build();
        return dto;
    }



    // 삭제
    @Transactional
    public void delCafe(Long id) {
        Cafe target = cafeRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("해당 Cafe 가 없습니다."+id)
                );
        cafeRepository.delete(target);
    }

}
