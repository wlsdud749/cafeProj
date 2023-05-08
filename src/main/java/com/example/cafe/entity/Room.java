package com.example.cafe.entity;

import com.example.cafe.dto.RoomDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="room")
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동증가
    private long idx;
    private String seat_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_idx")

    private Cafe cafe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;




}
