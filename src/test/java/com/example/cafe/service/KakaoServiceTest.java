package com.example.cafe.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class KakaoServiceTest {

    @Autowired
    KakaoService kakaoService;

    @Test
    void search() {
        kakaoService.search("스타벅스 대구");
    }

}