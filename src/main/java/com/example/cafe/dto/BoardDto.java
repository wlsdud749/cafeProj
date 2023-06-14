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

    private String title;
    private String content;

    private long user_idx;

    private BoardDto board_entityToDto(Board board) {

        BoardDto boardbto = BoardDto.builder()
                .idx(board.getIdx())
                .title(board.getTitle())
                .content(board.getContent())
                .user_idx(board.getUser().getIdx())
                .build();

        return boardbto;
    }

}
