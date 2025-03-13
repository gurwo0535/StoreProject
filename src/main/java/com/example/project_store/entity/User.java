package com.example.project_store.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity // 엔티티 클래스
//이 클래스가 JPA 엔티티임을 나타냄 (즉, 데이터베이스의 users 테이블과 연결됨)
@Builder // Users 객체를 빌더 패턴으로 생성할 수 있도록 함
@AllArgsConstructor
// 모든 필드를 포함하는 생성자 자동 생성
@NoArgsConstructor
// 기본 생성자 자동 생성 (JPA에서는 기본 생성자가 필수)
@Data
//  getter/setter 및 객체 정보 출력 메서드를 자동으로 생성해 줌


public class User {

    @Id // : 기본 키(PK) 설정

    private String id; // 개인 아아디
    private String password; // 개인 패스워드
    private String userName;// 개인 이름
    private String phoneNm;// 개인 폰 넘버
    private int role; // 기업과 일반


}
