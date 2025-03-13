package com.example.project_store.service;

import com.example.project_store.dto.StorerDto;
import com.example.project_store.entity.Reservation;
import com.example.project_store.entity.Storer;
import com.example.project_store.entity.User;
import com.example.project_store.repo.ReservationRepo;
import com.example.project_store.repo.StorerRepo;
import com.example.project_store.repo.UserRepo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final StorerRepo storerRepo;  // 필드 주입 방식
    private final UserRepo userRepo;
    private final ReservationRepo reservationRepo;


    //    public String registrationUp(StorerDto storerDto, HttpSession session) {
    public boolean registrationUp(StorerDto storerDto, HttpSession session) {
        try {
            Storer storer = Storer.builder()
                    .photo(storerDto.getPhoto())
                    .title(storerDto.getTitle())
                    .storePhone(storerDto.getStorePhone())
                    .address(storerDto.getAddress())
                    .description(storerDto.getDescription())
                    .businessHours(storerDto.getBusinessHours())
                    .price(storerDto.getPrice())
                    .user(checkLogin(session))
                    .build();
            storerRepo.save(storer);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public List<Storer> findAll(Model model, HttpSession session) {
        User user = checkLogin(session);

        // 로그인되지 않은 경우 처리
        if (user == null) {
            model.addAttribute("welcome", "게스트님 환영합니다.");
        } else {
            String msg = user.getUserName() + "님 환영합니다~";
            model.addAttribute("welcome", msg);  // 모델에 메시지 추가
        }
        try {
//            return storerRepo.findByUserId(user.getId());  // 사용자의 아이템 리스트 반환
            return storerRepo.findAll();// 모든 식당 정버 반환
        } catch (Exception e) {
            return List.of();  // 예외 발생 시 빈 리스트 반환
        }
    }

    private User checkLogin(HttpSession session) {
        String userName = (String) session.getAttribute("id");

        // 세션에서 userName이 없으면 로그인되지 않은 상태로 처리
        if (userName == null) {
            return null;  // 로그인되지 않은 경우 null 반환
        }

        // 로그인된 사용자 정보 조회
        User user = userRepo.findByUserName(userName);

        // 만약 찾을 수 없는 사용자라면 null을 반환
        return user != null ? user : null;
    }

    public Reservation findById(int id) {
        Optional<Reservation> reservation = reservationRepo.findById(id);
        return reservation.orElse(null);  // 없으면 null 반환
    }

    public void saveReservation(Reservation reservation1) {
        reservationRepo.save(reservation1);
    }

    @Transactional
    public void delete(Long id) {
        try {
            reservationRepo.deleteById(id);
            // id를 기준으로 삭제를 진행한다
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
