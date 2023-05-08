package com.example.cafe.entity;

import com.example.cafe.entity.Board;
import com.example.cafe.repository.BoardRepository;
import com.example.cafe.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BoardTest {

    @Autowired
    BoardRepository boardRepository;

    @Test
    void resBoard() {
        Board board = Board.builder()
                .title("제목 테스트2")
                .content("내용 테스트2")
                .build();

        board=boardRepository.save(board);
        System.out.println(board);


    }

}