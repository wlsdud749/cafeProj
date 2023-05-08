package com.example.cafe.dto;


import com.example.cafe.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDto {

    @Id
    private long idx;

    // 내용
    private String title;
    private String content;

    private long user_idx;
//    private String author;

    public BoardDto(Board board) {
    }
}
