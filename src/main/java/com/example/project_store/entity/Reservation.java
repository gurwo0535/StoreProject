package com.example.project_store.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity // 엔티티 클래스
//이 클래스가 JPA 엔티티임을 나타냄 (즉, 데이터베이스의 users 테이블과 연결됨)
@Builder // Users 객체를 빌더 패턴으로 생성할 수 있도록 함
@AllArgsConstructor
// 모든 필드를 포함하는 생성자 자동 생성
@NoArgsConstructor
// 기본 생성자 자동 생성 (JPA에서는 기본 생성자가 필수)
@Data
//  getter/setter 및 객체 정보 출력 메서드를 자동으로 생성해 줌


public class Reservation {

    @Id // : 기본 키(PK) 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    기본 키를 자동 증가(AUTO_INCREMENT) 방식으로 설정
//    데이터베이스가 자동으로 id 값을 증가시킴

    private Integer id; // 예약 번호  // why Integer
    private LocalDateTime reseDay;
//    private String userId; // fk
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "storer_id")
    private Storer storer;

    public void update(Reservation reservation) {
        this.reseDay = reservation.getReseDay();
    }
}
