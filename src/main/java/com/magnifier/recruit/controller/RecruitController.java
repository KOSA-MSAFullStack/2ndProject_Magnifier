// RecruitController.java
// 역할: 채용 공고 관련 웹 요청 및 응답 처리

package com.magnifier.recruit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.SQLException;
import java.util.List;

import com.magnifier.recruit.dto.RecruitDto;
import com.magnifier.recruit.service.RecruitService;

@Controller
@RequestMapping("/recruit")
public class RecruitController {

    @Autowired
    private RecruitService recruitService;

    // 채용 공고 등록 폼_GET
    @GetMapping("/register")
    public String registerForm() {
        return "recruit/register"; // recruit/register.jsp 뷰 반환
    }

    // 채용 공고 등록_POST
    // 예외 처리: RecruitDto 객체 유효성 검사 및 DB 저장 실패 시 예외 처리
    @PostMapping("/register")
    public String register(RecruitDto recruitDto) {
        try {
            // 채용 공고 등록 로직 구현
            System.out.println("채용 공고 등록: " + recruitDto.getTitle());
            return "redirect:/recruit/list"; // 등록 성공 시, 목록 페이지로 리다이렉트
        } catch (Exception e) {
            System.err.println("채용 공고 등록 중 오류 발생: " + e.getMessage());
            return "error/errorPage";
        }
    }

    // 전체 채용 공고 목록 조회_GET
    // 예외 처리: 채용 공고 목록 조회 실패 시 예외 처리
    @GetMapping("/list")
    public String list(Model model) throws SQLException {
        try {
            // 전체 채용 공고 목록 조회
            List<RecruitDto> recruitList = recruitService.getRecruitList(); 
            model.addAttribute("recruitList", recruitList);
            return "recruit/list"; // recruit/list.jsp 뷰 반환
        } catch (Exception e) {
            System.err.println("전체 채용 공고 목록 조회 중 오류 발생: " + e.getMessage());
            return "error/errorPage";
        }
    }

    // 기업별 등록한 채용 공고 목록 조회_GET
    // 예외 처리: 기업별 채용 공고 목록 조회 실패 시 예외 처리
    @GetMapping("/list/{enterpriseId}")
    public String listById(@PathVariable("enterpriseId") int enterpriseId, Model model) throws SQLException {
        try {
            // 기업별 등록한 채용 공고 목록 조회
            List<RecruitDto> recruitList = recruitService.getRecruitListById(enterpriseId);
            model.addAttribute("recruitList", recruitList);
            return "recruit/list"; // recruit/list.jsp 뷰 반환
        } catch (Exception e) {
            System.err.println("기업별 등록한 채용 공고 목록 조회 중 오류 발생 (기업 ID: " + enterpriseId + "): " + e.getMessage());
            return "error/errorPage";
        }
    }
}
