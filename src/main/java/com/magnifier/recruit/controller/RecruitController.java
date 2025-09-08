// RecruitController.java
// 채용 공고 관련 웹 요청 및 응답 처리

package com.magnifier.recruit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PostMapping("/register")
    public String register(@ModelAttribute RecruitDto recruitDto, Model model) { // Model 추가
        try {
            // enterpriseId가 비어있는지 확인 (로그인하지 않은 사용자의 접근 차단)
            if (recruitDto.getEnterpriseId() == null) {
                model.addAttribute("error", "로그인이 필요합니다.");
                return "enterprise/login"; // 로그인 페이지로 리다이렉트
            }

            // 채용 공고 등록 로직 구현
            recruitService.insertRecruit(recruitDto); // 서비스 계층에 공고 등록 요청
            System.out.println("채용 공고 등록: " + recruitDto.getTitle());
            return "redirect:/recruit/list"; // 등록 성공 시, 목록 페이지로 리다이렉트
        } catch (Exception e) {
            System.err.println("채용 공고 등록 중 오류 발생: " + e.getMessage());
            return "error/errorPage";
        }
    }

    // 전체 채용 공고 목록 조회_GET
    @GetMapping("/list")
    public String list(Model model) {
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
    @GetMapping("/list/{enterpriseId}")
    public String listById(@PathVariable("enterpriseId") int enterpriseId, Model model) {
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

    // 채용 공고 상세 조회_GET
    @GetMapping("/detail/{recruitId}")
    public String detail(@PathVariable("recruitId") int recruitId, Model model) {
        try {
            // 채용 공고 상세 정보 조회
            RecruitDto recruitDto = recruitService.detailRecruit(recruitId);
            model.addAttribute("recruitDto", recruitDto);
            return "recruit/detail"; // recruit/detail.jsp 뷰 반환
        } catch (Exception e) {
            System.err.println("채용 공고 상세 조회 중 오류 발생 (공고 ID: " + recruitId + "): " + e.getMessage());
            return "error/errorPage";
        }
    }

    // 채용 공고 수정 폼_GET
    @GetMapping("/modify/{recruitId}")
    public String modifyForm(@PathVariable("recruitId") int recruitId, Model model) {
        try {
            // 기존 채용 공고 정보 로드
            RecruitDto recruitDto = recruitService.detailRecruit(recruitId);
            model.addAttribute("recruitDto", recruitDto);
            return "recruit/modify"; // recruit/modify.jsp 뷰 반환
        } catch (Exception e) {
            System.err.println("채용 공고 수정 폼 로드 중 오류 발생 (공고 ID: " + recruitId + "): " + e.getMessage());
            return "error/errorPage";
        }
    }

    // 채용 공고 수정_POST
    @PostMapping("/modify")
    public String modify(RecruitDto recruitDto) {
        try {
            // 채용 공고 수정 로직 구현
            recruitService.updateRecruit(recruitDto);
            System.out.println("채용 공고 수정: " + recruitDto.getTitle());
            return "redirect:/recruit/detail/" + recruitDto.getRecruitId(); // 수정 성공 시, 상세 페이지로 리다이렉트
        } catch (Exception e) {
            System.err.println("채용 공고 수정 중 오류 발생: " + e.getMessage());
            return "error/errorPage";
        }
    }

    // 채용 공고 삭제_POST
    @PostMapping("/delete")
    public String delete(RecruitDto recruitDto) {
        try {
            // 채용 공고 삭제 로직 구현
            recruitService.deleteRecruit(recruitDto);
            System.out.println("채용 공고 삭제 (ID): " + recruitDto.getRecruitId());
            return "redirect:/recruit/list"; // 삭제 성공 시, 목록 페이지로 리다이렉트
        } catch (Exception e) {
            System.err.println("채용 공고 삭제 중 오류 발생 (ID: " + recruitDto.getRecruitId() + "): " + e.getMessage());
            return "error/errorPage";
        }
    }
}
