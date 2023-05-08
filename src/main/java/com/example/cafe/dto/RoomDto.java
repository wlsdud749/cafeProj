package com.example.cafe.dto;

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
public class RoomDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동증가
    private long idx;

    //좌석번호
    private String seat_name;
    private long cafe_idx;
    private long user_idx;
}
