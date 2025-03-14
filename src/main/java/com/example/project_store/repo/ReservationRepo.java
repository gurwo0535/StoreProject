package com.example.project_store.repo;

import com.example.project_store.entity.Reservation;
import com.example.project_store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

//UsersRepo는 Users 테이블과 연결된 데이터베이스 접근 레이어 역할을 합니다.
// 기본적인 CRUD 기능은 JpaRepository가 자동으로 제공하며,
// 추가적으로 existsByUsernameAndPassword()와 existsByUsername()을 구현하여
// 로그인 및 회원가입 검증 기능을 수행할 수 있습니다.

public interface ReservationRepo extends JpaRepository<Reservation, Integer> {

    List<Reservation> findByUserId(String userId);

//    Optional<Reservation> findById(Long id);
    List<Reservation> findByUser(User user);
    void deleteById(Long id);
}
