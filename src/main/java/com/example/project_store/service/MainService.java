package com.example.project_store.service;

import com.example.project_store.entity.User;
import com.example.project_store.repo.UserRepo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
public class MainService {


    private final UserRepo userRepo;

    private  final HttpSession session;


    public String login(
            String id,
            String password,
            HttpSession session,
            Model model
            ){

//        boolean check = userRepo.findByIdAndPassword(id,password);
        User user = userRepo.findByIdAndPassword(id,password);
        if (user != null) {
            session.setAttribute("id",id);
            session.setAttribute("role", user.getRole());
            model.addAttribute("msg","로그인 성공");
            return "/main"; // model에 글씨나 정보 저장하고 redirect하면 model정보가 삭제

        }
        else {
            model.addAttribute("msg","로그인 실패");
            return "/login";
        }
    }

}
