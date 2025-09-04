// RecruitController.java
// 역할: 채용 정보 관련 웹 요청 및 응답 처리

package com.magnifier.recruit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.magnifier.recruit.dto.RecruitDto;

@Controller
@RequestMapping("/recruit")
public class RecruitController {

    // 채용 정보 등록 폼
    // GET 요청 처리
    @GetMapping("/register")
    public String registerForm() {
        return "recruit/register"; // recruit/register.jsp 뷰 반환
    }

    // 채용 정보 등록 처리
    // POST 요청 처리
    @PostMapping("/register")
    public String register(RecruitDto recruitDto) {
        try {
            // 서비스 계층 호출 --> 채용 정보 등록 로직 구현
            System.out.println("채용 정보 등록: " + recruitDto.getTitle());
            return "redirect:/recruit/list"; // 등록 성공 시, 목록 페이지로 리다이렉트
        } catch (Exception e) {
            System.err.println("채용 정보 등록 중 오류 발생: " + e.getMessage());
            return "error/errorPage"; // 에러 페이지 반환
        }
    }
}
