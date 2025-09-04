package com.magnifier.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import lombok.extern.log4j.Log4j;

@Log4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * 권한이 없는 사용자가 보호된 리소스에 접근하려 할 때 호출되는 메서드
     * @param request 현재 HTTP 요청 정보
     * @param response HTTP 응답 객체
     * @param accessDeniedException 권한 거부 예외 객체
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 권한 거부 발생 로그 기록
        log.error("Access Denied Handler");
        log.error("Redirect...");

        // 권한 거부 시 사용자에게 보여줄 /accessError 페이지로 리다이렉트 처리
        response.sendRedirect("/accessError");
    } // end handle

} // end CustomAccessDeniedHandler
