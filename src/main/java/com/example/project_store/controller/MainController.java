package com.example.project_store.controller;


import com.example.project_store.dto.ReservationDto;
import com.example.project_store.dto.StorerDto;
import com.example.project_store.dto.UserDto;
import com.example.project_store.entity.Reservation;
import com.example.project_store.service.ReservationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.project_store.service.MainService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller // 컨트롤러를 사용한다
@RequiredArgsConstructor   // 서비스단과 연결 시켜준다
public class MainController {
    private final MainService mainService;
    private final ReservationService reservationService;

    @GetMapping("/main")
    public String mainPage(HttpSession session, Model model) {
        Object loginUser = session.getAttribute("loginUser"); // 로그인한 사용자 정보 가져오기
        Integer userRole = (Integer) session.getAttribute("role"); // role 값 가져오기

        model.addAttribute("loginUser", loginUser);
        model.addAttribute("userRole", userRole);

        return "/main";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @PostMapping("/login")
    public String loginPost(@RequestParam String id, @RequestParam String password,
                            // @RequestParam을 사용해 사용자가 입력한 username과 password를 받아옴.
                            HttpSession session, Model model) {
        String result = mainService.login(id, password, session, model);

        if (result.equals("/main")) {
            session.setAttribute("userId", id); //  세션에 userId 저장
            session.setAttribute("loginUser", id);

            return "redirect:/main";

        }
        return result;

    }

    @GetMapping("/myPage")
    public String showMyPage(HttpSession session, Model model) {
        // 세션에서 userId를 가져오기
        String userId = (String) session.getAttribute("userId");

        if (userId != null) {
            List<Reservation> reservations = reservationService.getUserReservations(userId);
            model.addAttribute("reservations", reservations); // 모델에 예약 리스트 추가
        }
        return "/myPage";
    }

    @PostMapping("/myPage")
    public String myPage(@ModelAttribute ReservationDto reservationDto, HttpSession session, Model model) {

        boolean success = reservationService.reservationUp(reservationDto, session);
        if (success) {
            return showMyPage(session, model);
        } else {
            model.addAttribute("msg", "식당 예약 실패");
            return "/reservation";
        }
    }


}
