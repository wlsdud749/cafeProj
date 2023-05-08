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
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class RoomService {


    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final CafeRepository cafeRepository;


    // 엔티티 -> Dto
    private RoomDto entityToDto(Room room) {
        return RoomDto.builder()
                .idx(room.getIdx())
                .seat_name(room.getSeat_name())
                .cafe_idx(room.getCafe().getIdx())
                .user_idx(room.getUser().getIdx())
                .build();
    }

    // Dto -> 엔티티
    public Room toEntity(RoomDto roomDto, Cafe cafe, User user) {
        return Room.builder()
                .idx(roomDto.getIdx())
                .seat_name(roomDto.getSeat_name())
                .cafe(cafe)
                .user(user)
                .build();
    }


//    일단 킵
//    public void addSeatInfo(CafeResDto cafe, UserDto user, RoomDto roomDto) {
//        // 넘길 때 필요한 정보, user 이름 , 좌석 번호, 카페 이름
//
//        // 좌석 번호
//        String seat_name = roomDto.getSeat_name();
//        var cafeEntity = cafeRepository.findById(cafe.getIdx()).get();
//        System.out.println("cafeEntity 확인:" + cafeEntity);
//        var userEntity = userRepository.findById(user.getIdx()).get();
//        System.out.println("cafeEntity 확인:" + userEntity);
//    }
}



