package com.example.project_store.dto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data

public class StorerDto {

    private int id; // 가게 아이디
    private String title; // 가게 이름
    private String description; // 가게 내용
    private String photo; // 사진
    private String  storePhone; // 가게 전화 번호
    private String address; // 가게 주소
    private LocalDate createdTime;// 작성일
    private LocalDate updatedTime;//수정일
    private String businessHours; // 가게 영업 시간
    private int price; // 가게 가격
    private String userid; // pk


}
