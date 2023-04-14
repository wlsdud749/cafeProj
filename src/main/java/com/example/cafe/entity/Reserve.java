package com.example.cafe.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reserve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동증가
    private long idx;
    private long user_idx;
    private long room_idx;
    private long order_idx;
    private LocalDateTime from_time;
    private LocalDateTime to_time;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
