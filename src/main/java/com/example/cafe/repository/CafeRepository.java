package com.example.cafe.repository;

import com.example.cafe.entity.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CafeRepository extends JpaRepository<Cafe, Long> {
    Optional<List<Cafe>> findByUserIdx(Long userId);

//    Optional<List<Cafe>> findByCafeName(String name);
}
