package com.example.project_store.controller;

import com.example.project_store.entity.Reservation;
import com.example.project_store.entity.User;
import com.example.project_store.repo.StorerRepo;
import com.example.project_store.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller // 컨트롤러를 사용한다
@RequiredArgsConstructor   // 서비스단과 연결 시켜준다
@RequestMapping("/reservation") // ?
public class ReservationController {

    @Autowired
    private final RegistrationService registrationService;
    private final StorerRepo storerRepo;


    @GetMapping("/{id}")
    public String showReservationPage(@PathVariable("id") Long storeId, Model model) {
        String storeName = registrationService.getStoreNameById(storeId);
        model.addAttribute("msg1", "예약 정보를 입력하세요.");
        model.addAttribute("storeId", storeId); // 선택한 식당 ID를 전달
        model.addAttribute("storeName", storeName);  // storeName을 모델에 추가
        return "reservation";
    }





    @GetMapping("/edit/{id}")
    public String editReservation(@PathVariable int id, Model model) {
        Reservation reservation = registrationService.findById(id);
        model.addAttribute("reservation", reservation);
        return "edit";
    }

    @PostMapping("/update")
    public String updateReservation(@ModelAttribute Reservation reservation) {
        Reservation reservation1 = registrationService.findById(reservation.getId());
        reservation1.update(reservation);
        registrationService.saveReservation(reservation1);
        return "redirect:/myPage";
    }

    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable Long id //삭제할 게시글의 ID를 URL에서 추출.
    ) {
        registrationService.delete(id);   //호출해 해당 게시글 삭제.
        return "redirect:/myPage";
    }
}

