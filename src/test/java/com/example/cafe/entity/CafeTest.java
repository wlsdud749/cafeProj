package com.example.cafe.entity;

import com.example.cafe.repository.CafeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CafeTest {

    @Autowired
    CafeRepository cafeRepository;

    @Test
    void cafeAddTest() {
        var cafe = Cafe.builder()
                .name("스타벅스 대구가톨릭대점")
                .addr("경북 경산시 하양읍 하양로 18")
                .addr2("경북 경산시 하양읍 금락리 229-13")
                .number("1522-3232")
                .x("x좌표")
                .y("y좌표")
                .table_cnt(6)
                .build();

        cafe = cafeRepository.save(cafe);
        System.out.println(cafe);
    }
}