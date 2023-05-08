package com.example.cafe.repository;


import com.example.cafe.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository <Room,Long> {

    Optional<List<Room>> findByCafeIdx(Long cafeIdx);
}
