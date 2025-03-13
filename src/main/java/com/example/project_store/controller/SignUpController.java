package com.example.project_store.controller;

import com.example.project_store.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor

public class SignUpController {

    private  final com.example.project_store.service.signupService signupService ;
    @GetMapping("/signUp")
    public String getSignup(){
        return "/signUp";
    }

    @PostMapping("/signUp")
    private  String postSignup(
            @ModelAttribute UserDto dto,
            Model model
            ){
        String msg = signupService.signUp(dto);
        if (msg.equals("회원가입 성공")) {
            return "redirect:/login";
        }
        model.addAttribute("msg",msg);
        return "/signUp";
    }
}
