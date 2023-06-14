package com.example.cafe.entity;


import com.example.cafe.dto.BoardDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동증가
    private long idx;

    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private final List<Board> boards = new ArrayList<>(); // User 엔티티와의 OneToMany 관계를 정의

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "user_idx")
    @ToString.Exclude
    private User user;

    private String title;
    private String content;
//    private String author;

    private Board board_dtoToEntity(BoardDto boardDto) {

        var dto = Board.builder()
                .idx(boardDto.getIdx())
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .build();

        return dto;
    }

}
