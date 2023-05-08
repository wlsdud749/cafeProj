package com.example.cafe.entity;


import com.example.cafe.entity.listener.LibraryEntityListener;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@EntityListeners(value = { LibraryEntityListener.class })
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="user")

public class User {


    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동증가
    private Long idx;

    private String id;
    private String passwd;
    private String name;
    private String nick;
    private String addr;
    private int owner;

    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private final List<Cafe> cafes = new ArrayList<>(); // Cafe 엔티티와의 OneToMany 관계를 정의

    // 편의 메서드
    public void addCafe(Cafe cafe) {
        cafes.add(cafe);

    }




}
