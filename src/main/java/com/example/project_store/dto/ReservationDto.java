package com.example.project_store.dto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
public class ReservationDto {

    private int id; // 예약 아이디
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")  // 예상되는 날짜-시간 형식 지정
    private LocalDateTime reseDay;
    private String userId; // fk
    private  Integer storeId;




}
