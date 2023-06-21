package com.example.cafe.service;

import com.example.cafe.dto.BoardDto;
import com.example.cafe.entity.Board;
import com.example.cafe.repository.BoardRepository;
import com.example.cafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor


public class BoardService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    public BoardDto resBoard(BoardDto dto, Long idx) {


        var user1 = userRepository.findByIdx(idx);
        Board board = Board.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .user(user1.get())
                .build();

        boardRepository.save(board);
        board_entityToDto(board);

        return dto;
    }

    private BoardDto board_entityToDto(Board board) {

        BoardDto boardDto = BoardDto.builder()
                .idx(board.getIdx())
                .title(board.getTitle())
                .content(board.getContent())
                .user_idx(board.getUser().getIdx())
                .build();

        return boardDto;
    }

    public Board findBoard(Long idx) {
        return boardRepository.findById(idx).get();
    }

    @Transactional
    public void delBoard(Long idx) {

        Board target = boardRepository.findById(idx)
                .orElseThrow(
                        () -> new IllegalArgumentException("해당 Board 가 없습니다."+idx)
                );
        boardRepository.delete(target);
    }

    @Transactional
    public Board updateBoard(Long idx, String title, String content) {


        Board board = boardRepository.findById(idx).get();
        board.setTitle(title);
        board.setContent(content);

        return board;

    }


}
