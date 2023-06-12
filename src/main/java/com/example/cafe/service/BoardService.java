package com.example.cafe.service;

import com.example.cafe.dto.BoardDto;
import com.example.cafe.dto.UserDto;
import com.example.cafe.entity.Board;
import com.example.cafe.entity.User;
import com.example.cafe.repository.BoardRepository;
import com.example.cafe.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor


public class BoardService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    public BoardDto resBoard(BoardDto dto,Long idx) {


        var user1 = userRepository.findByIdx(idx);
        Board board = Board.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .user(user1.get())
                .build();

        boardRepository.save(board);
        board_entityToDto(board);

//        System.out.println("테스트"+board);

        return dto;
    }

    private BoardDto board_entityToDto(Board board) {

        BoardDto boardbto = BoardDto.builder()
                .idx(board.getIdx())
                .title(board.getTitle())
                .content(board.getContent())
                .user_idx(board.getUser().getIdx())
                .build();

        return boardbto;
    }

    private Board board_dtoToEntity(BoardDto boardDto) {

        var dto = Board.builder()
                .idx(boardDto.getIdx())
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .build();

        return dto;
    }



}
