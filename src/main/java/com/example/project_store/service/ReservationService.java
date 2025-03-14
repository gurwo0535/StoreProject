package com.example.project_store.service;

import com.example.project_store.dto.ReservationDto;
import com.example.project_store.entity.Reservation;
import com.example.project_store.entity.Storer;
import com.example.project_store.entity.User;
import com.example.project_store.repo.ReservationRepo;
import com.example.project_store.repo.StorerRepo;
import com.example.project_store.repo.UserRepo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepo reservationRepo;
    private final StorerRepo storerRepo;
    private final UserRepo userRepo; // 유저 정보 가져오기 위해 추가

    public boolean reservationUp(ReservationDto reservationDto, HttpSession session) {

            String userId = (String) session.getAttribute("userId"); // 세션에서 userId 가져오기
        System.out.println("reservationUp()에서 가져온 userId: " + userId); // ✅ 디버깅용 로그
            if (userId == null) {
                return false;
            }
            User user = userRepo.findById(userId).orElse(null);
            if (user == null) {
                return false;
            }
        Storer storer = storerRepo.findById(reservationDto.getStoreId()).get();

        Reservation reservation = Reservation.builder()
                .reseDay(reservationDto.getReseDay())
                .storer(storer)
                .user(user)
                .build();

         reservationRepo.save(reservation);
        return true;
    }

    public List<Reservation> getUserReservations(String userId) {

        User user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            return List.of(); // 유저가 없으면 빈 리스트 반환
        }
        return reservationRepo.findByUser(user);
    }




}
