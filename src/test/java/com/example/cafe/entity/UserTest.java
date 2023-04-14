package com.example.cafe.entity;

import com.example.cafe.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void userAddTest() {
        var user = User.builder()
                .id("bokT")
                .passwd("1234")
                .name("복성민 선생")
                .addr("혁진 마음 속")
                .nick("복선생")
                .owner(1)
                .build();

        user = userRepository.save(user);
        System.out.println(user);
    }

    @Test
    void findUserTest() {
        System.out.println("시작지점 ------");
        // 이거 user_idx 가 있어야 찾는거 같은데..?
        // 빙고. DB 에 컬럼명이 이상해서 그랬다.
        var optionalUser = userRepository.findById(1L);
//        if(optionalUser.isPresent()){
//            System.out.println(optionalUser.get());
//        }

        optionalUser.ifPresent(System.out::println);

    }
}
