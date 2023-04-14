package com.example.cafe.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Menu {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동증가
    private long idx;
    private  String name;
    private int price;

    // 조인 컬럼 수정해야댐
    private long cafe_idx;
    
}
