package com.example.cafe.repository;

import com.example.cafe.entity.Board;
import com.example.cafe.entity.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {

    List<Board> findByUserIdx(Long userId);


}
