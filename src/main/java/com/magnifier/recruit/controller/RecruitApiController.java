// RecruitApiController.java
// 역할: 채용 공고 관련 RESTful API 요청 및 응답 처리
/*
 * 설명:
 * - 클라이언트의 채용 공고 관련 RESTful API 요청 처리
 * - 서비스 계층과 연동하여 비즈니스 로직 수행
 * - JSON 형식의 데이터 응답 반환
 *
 * 주요 기능:
 * - 채용 공고 등록 (API)
 * - 채용 공고 목록 조회 (전체, 기업별 API)
 */


package com.magnifier.recruit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.Collections; // Collections import 추가
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import com.magnifier.recruit.dto.RecruitDto;
import com.magnifier.recruit.service.RecruitService;
import com.magnifier.security.domain.CustomEnterprise;

@RestController // RESTful API 컨트롤러임을 명시
@RequestMapping("/recruits/api") // API 엔드포인트 경로 설정
public class RecruitApiController {

    @Autowired
    private RecruitService recruitService;

    // 채용 공고 등록_POST (RESTful API)
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RecruitDto recruitDto, Authentication auth) {
        try {
            // 현재 로그인한 기업 회원의 ID를 Authentication 객체에서 가져와 설정
            // 보안 강화를 위해 클라이언트에서 보낸 ID가 아닌 서버의 인증 정보 사용
            CustomEnterprise customEnterprise = (CustomEnterprise) auth.getPrincipal();
            recruitDto.setEnterpriseId(customEnterprise.getEnterpriseId());

            // 채용 공고 등록 서비스 호출
            recruitService.insertRecruit(recruitDto);
            System.out.println("채용 공고 등록: " + recruitDto.getTitle());

            return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("채용 공고 등록 중 오류 발생: " + e.getMessage());
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 전체 채용 공고 목록 조회_GET (RESTful API)
    @GetMapping(value = "/list", produces = "application/json; charset=UTF-8")
    public ResponseEntity<Map<String, Object>> list(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        try {
            // 서비스에서 페이지네이션 적용된 목록 가져오기
            List<RecruitDto> recruitList = recruitService.getRecruitList(page, size);
            // 전체 아이템 수 가져오기
            int totalCount = recruitService.getCount();
            // 전체 페이지 수 계산
            int totalPages = (int) Math.ceil((double) totalCount / size);

            // 응답 데이터 구성
            Map<String, Object> response = new HashMap<>();
            response.put("recruits", recruitList);
            response.put("currentPage", page);
            response.put("totalPages", totalPages);
            response.put("totalCount", totalCount);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("전체 채용 공고 목록 조회 중 오류 발생: " + e.getMessage());
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 기업별 등록한 채용 공고 목록 조회_GET (RESTful API)
    @GetMapping("/listbyid")
    public ResponseEntity<List<RecruitDto>> listById(Authentication auth) {
        try {
            // 인증 정보 및 사용자 타입 확인
            if (auth != null && auth.getPrincipal() instanceof CustomEnterprise) {
                CustomEnterprise customEnterprise = (CustomEnterprise) auth.getPrincipal();
                int enterpriseId = customEnterprise.getEnterpriseId();

                // 해당 기업이 등록한 채용 공고 목록 조회 서비스 호출
                List<RecruitDto> recruitList = recruitService.getRecruitListById(enterpriseId);
                return new ResponseEntity<>(recruitList, HttpStatus.OK);
            } else {
                // 기업 회원이 아니거나 인증 정보가 없는 경우
                System.err.println("기업별 등록한 채용 공고 목록 조회 권한 없음 (비기업 사용자 또는 비로그인)");
                return new ResponseEntity<>(Collections.emptyList(), HttpStatus.FORBIDDEN); // 403 Forbidden
            }
        } catch (Exception e) {
            String principalInfo = "N/A";
            if (auth != null && auth.getPrincipal() instanceof CustomEnterprise) {
                principalInfo = "기업 ID: " + ((CustomEnterprise) auth.getPrincipal()).getEnterpriseId();
            } else if (auth != null) {
                principalInfo = "사용자: " + auth.getPrincipal().toString();
            }
            System.err.println("기업별 등록한 채용 공고 목록 조회 중 오류 발생 (" + principalInfo + "): " + e.getMessage());
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 채용 공고 상세 조회_GET (RESTful API)
    @GetMapping("/detail/{recruitId}")
    public ResponseEntity<RecruitDto> detail(@PathVariable("recruitId") int recruitId) {
        try {
            RecruitDto recruitDto = recruitService.detailRecruit(recruitId);
            return new ResponseEntity<>(recruitDto, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("채용 공고 상세 조회 중 오류 발생 (공고 ID: " + recruitId + "): " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 채용 공고 수정_PUT (RESTful API)
    @PutMapping("/modify/{recruitId}")
    public ResponseEntity<String> modify(@PathVariable("recruitId") int recruitId, @RequestBody RecruitDto recruitDto) {
        try {
            recruitDto.setRecruitId(recruitId);
            recruitService.updateRecruit(recruitDto);
            System.out.println("채용 공고 수정: " + recruitDto.getTitle());
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("채용 공고 수정 중 오류 발생: " + e.getMessage());
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 채용 공고 삭제_DELETE (RESTful API)
    @DeleteMapping("/delete/{recruitId}")
    public ResponseEntity<String> delete(@PathVariable("recruitId") int recruitId) {
        try {
            RecruitDto recruitDto = new RecruitDto();
            recruitDto.setRecruitId(recruitId);
            recruitService.deleteRecruit(recruitDto);
            System.out.println("채용 공고 삭제 (ID): " + recruitId);
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("채용 공고 삭제 중 오류 발생 (ID: " + recruitId + "): " + e.getMessage());
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
