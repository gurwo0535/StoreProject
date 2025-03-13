package com.example.project_store.controller;

import com.example.project_store.dto.StorerDto;
import com.example.project_store.entity.Storer;
import com.example.project_store.service.RegistrationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // 컨트롤러를 사용한다
@RequiredArgsConstructor   // 서비스단과 연결 시켜준다
public class RegistrationController {
    private final RegistrationService registrationService;

    @GetMapping("/storeRegistration")
    public String StoreRegistration() {
        return "/storeRegistration";
    }


    //템플릿(storeList.html)에서 storers를 사용할 수 있도록 추가.
    @GetMapping("/storeList")
    public String storeList(
            Model model,
            HttpSession session
    ) {
        List<Storer> storers = registrationService.findAll(model, session);
        model.addAttribute("storers", storers);
        return "/storeList";
    }


    @PostMapping("/storeRegistration")
    public String StorePost(
            @ModelAttribute StorerDto storerDto,
            Model model,
            HttpSession session
    ) {
//        String msg = registrationService.registrationUp(storerDto,session); // 기존
        boolean success = registrationService.registrationUp(storerDto, session);
        if (success) {
            return "redirect:/storeList";
        } else {
            model.addAttribute("msg", "식당 등록에 실패하였습니다.");
            return "/storeRegistration";
        }
    }
}
