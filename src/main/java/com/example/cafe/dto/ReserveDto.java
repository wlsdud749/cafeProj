package com.example.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReserveDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동증가
    private long idx;
    private long user_idx;
    private long room_idx;


//    일단 시간은 keep..
//    private LocalDateTime from_time;
//    private LocalDateTime to_time;
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
}
