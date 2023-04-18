package com.example.cafe.service;

import com.example.cafe.dto.UserDto;
import com.example.cafe.entity.Cafe;
import com.example.cafe.entity.User;
import com.example.cafe.repository.CafeRepository;
import com.example.cafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
// 생성자 주입 방법 중 하나. RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final CafeRepository cafeRepository;


    @Transactional
    public UserDto findByIdAndPasswd(UserDto userDto) {
        // 받아옴
//        System.out.println(userDto.getId() + "ㅁㅁㅁㅁㅁ" + userDto.getPasswd());
        Optional<User> user = userRepository.findByIdAndPasswd(userDto.getId(), userDto.getPasswd());
        // 갑자기 이거도 null 값
        System.out.println("findByIdAndPasswd에서 가져온 유저 정보. " + user.get());
        // 정보가 없다고 한다 ..
        // idx , userId, userPw 가 널값이다.. 왜지?
        if (user.isPresent()) {
//            System.out.println("user 정보(findByIdAndPasswd):" + user.get());
            return entityToDto(userRepository.findByIdAndPasswd(userDto.getId(), userDto.getPasswd()).get());
        }
        return null;
    }


    @Transactional
    public Optional<List<Cafe>> getList(Long idx) {
        var list = cafeRepository.findByUserIdx(idx);
//        idx = 1 번인 정보만 가져온다
//        var list = userRepository.findByIdx(idx);
//        그럼, idx 에 따른 정보를 가져오려면?
        // idx = user_idx 가 같은 것.
        // 게시판에 보여 질 때 사용
        System.out.println("이게 뭘 의미? (리스트 뽑아온 것. user 테이블의 idx 와 cafe 테이블에 있는 user_idx 값과 동일한 것):\n"
                + list.get());
        return list;
    }

    private UserDto entityToDto(User user) {
        var dto = UserDto.builder()
                .idx(user.getIdx())
                // idx 도 꼭 dto 에 추가해야한다. 안그러면 받아오질 못함.
                .id(user.getId())
                .passwd(user.getPasswd())
                .name(user.getName())
                .addr(user.getAddr())
                .nick(user.getNick())
                .owner(user.getOwner())
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

    //회원가입?
    public UserDto registerUser(UserDto dto) {
        User user = null;

        if (dto.getIdx() == null) user = userRepository.save(dtoToEntity(dto));
        else {
            var entity = userRepository.findById(dto.getIdx()).get();
            entity.setName(dto.getName());
            entity.setId(dto.getId());
            entity.setPasswd(dto.getPasswd());
            entity.setAddr(dto.getAddr());
            entity.setNick(dto.getNick());
            entity.setOwner(dto.getOwner());
        }
        return entityToDto(user);

    }
}