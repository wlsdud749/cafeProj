package com.example.cafe.repository;

import com.example.cafe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByIdAndPasswd(String id, String passwd);

//    이딴거 누가 가르쳐 줬노 - 복쌤
//    Optional<User> findByName(String name);
    Optional<User> findByIdx(Long idx);
//    Optional<User> findByUserIdx(Long idx);
    // jpa 사용시, 변수명에 주의하자. 없으면 의존성 오류가 남
}


