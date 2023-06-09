package com.example.cafe.service;


import com.example.cafe.dto.CafeResDto;
import com.example.cafe.dto.RoomDto;
import com.example.cafe.dto.UserDto;
import com.example.cafe.entity.Cafe;
import com.example.cafe.entity.Room;
import com.example.cafe.entity.User;
import com.example.cafe.repository.CafeRepository;
import com.example.cafe.repository.RoomRepository;
import com.example.cafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CafeService {
    private final CafeRepository cafeRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

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

//        System.out.println("user 정보 확인 ----- ******\n "+user);
        // 정보는 잘 전달 됨
//         System.out.println("정보 확인용 ------- \n"+cafeResDto);
        // idx 가 나오긴하는데
        // user 정보 확인 -----
        // UserDto(idx=1, id=Bok2, passwd=1234, name=null, nick=복선생2, addr=복 선생 마음 속2, owner=0)
//        System.out.println("user_idx 정보 확인 ------ *****"+user.getIdx());
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
//        System.out.println("정보 확인용 (Cafe Repository) -->" + cafe);
        // CafeRestDto 의 idx 값은 널이 당연하다고 함.
        // idx 가 널 값.
        // CafeResDto로 변환하여 반환
        CafeResDto cafeDto = entityToDto(cafe);
        cafeDto.setUser_idx(user.getIdx());
        return cafeDto;
    }

    @Transactional
    public CafeResDto getCafeInfo(Long idx, String seat_id,  UserDto userDto) {
        Optional<Cafe> cafe = cafeRepository.findById(idx);
        CafeResDto cafeResDto = null;
        if (cafe.isPresent()) {
            cafeResDto = entityToDto(cafe.get());
            Room room = Room.builder()
                    .seat_name(seat_id)
                    .cafe(cafe.get())
                    .user(dtoToEntity(userDto))
                    .build();
            roomRepository.save(room);
        }
        return cafeResDto;
    }
    @Transactional
    public List<RoomDto> getRoom(Long idx) {
        Optional<List<Room>> cafeRoom = roomRepository.findByCafeIdx(idx);
        List<RoomDto> roomList = new ArrayList<>();
        if(cafeRoom.isPresent()) {
            for(Room room: cafeRoom.get()) {
                RoomDto roomDto = room_entityToDto(room);
                roomList.add(roomDto);
            }
        }
        return roomList;
    }

    private RoomDto room_entityToDto(Room room) {
        RoomDto roomDto = RoomDto.builder()
                .idx(room.getIdx())
                .cafe_idx(room.getCafe().getIdx())
                .seat_name(room.getSeat_name())
                .user_idx(room.getUser().getIdx())
                .build();

        return roomDto;
    }

    @Transactional
    public CafeResDto getCafe(Long idx) {
        Optional<Cafe> cafe = cafeRepository.findById(idx);
        return CafeResDto.builder()
                .idx(cafe.get().getIdx())
                .name(cafe.get().getName())
                .build();
    }


    // 프론트에 던저주기 위해서 entityToDto 를 사용
    private CafeResDto entityToDto(Cafe cafe) {
        var dto = CafeResDto.builder().
                name(cafe.getName())
                .idx(cafe.getIdx())
                .addr(cafe.getAddr())
                .addr2(cafe.getAddr2())
                .xValue(cafe.getX())
                .yValue(cafe.getY())
                .number(cafe.getNumber())
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

    User dtoToEntity(UserDto userDto) {
        var dto = User.builder()
                .idx(userDto.getIdx())
                // idx 도 꼭 dto 에 추가해야한다. 안그러면 받아오질 못함.
                .id(userDto.getId())
                .passwd(userDto.getPasswd())
                .name(userDto.getName())
                .addr(userDto.getAddr())
                .nick(userDto.getNick())
                .owner(userDto.getOwner())
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
