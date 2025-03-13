package com.example.project_store.service;

import com.example.project_store.dto.UserDto;
import com.example.project_store.entity.User;
import com.example.project_store.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


//사용자 회원가입(signUp) 기능을 담당하는 서비스 클래스입니다.
//사용자가 입력한 정보()를 검증한 후, 새로운 엔티티를 데이터베이스에 저장


@Service
@RequiredArgsConstructor
public class signupService {
    private final UserRepo userRepo;

    public String signUp(UserDto dto) {
        //클라이언트가 전달한 회원가입 정보 (DTO) 담겨있다

        try {
            if (userRepo.existsById(dto.getId())) {
                return "아이디 중복 되었습니다.";
                // userRepo를 통해 user테이블의 중복을 확인하는 작업
            }
            if (!dto.getPassword().equals(dto.getPassword2())) {
                // 불일치 할 경우
                return "비밀번호가 일치하지않습니다.";
            }
            User user = User.builder()
                    .role(dto.getRole())
                    .userName(dto.getUserName())
                    .id(dto.getId())
                    .phoneNm(dto.getPhoneNm())
                    .password(dto.getPassword())
                    .build();
            userRepo.save(user);


//            데이터베이스에 저장 (userRepo.save(users))
            return "회원가입 성공";
//            회원가입 성공 시 반환"true"

        } catch (Exception e) {
            return "회원가입 실패";

        }
    }

    ;
}
