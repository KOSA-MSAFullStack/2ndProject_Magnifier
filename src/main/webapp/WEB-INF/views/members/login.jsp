<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- JSP 페이지 인코딩 및 컨텐츠 타입 설정 -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- JSTL core 라이브러리 선언 -->

<c:set var="path" value="${pageContext.request.contextPath}" />
<!-- JSP 변수 path에 웹 애플리케이션 컨텍스트 경로 저장 -->

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>로그인 | 개인회원</title>
    <link rel="stylesheet" href="/resources/css/login.css">
    <link rel="stylesheet" href="/resources/css/common.css">
    <!-- 로그인 및 공통 스타일시트 연결 -->

    <link href="https://fonts.googleapis.com/css?family=Inter:400,700&display=swap" rel="stylesheet">
    <!-- 구글 폰트 Inter 불러오기 -->
</head>
<body>
    <div class="background">
   		<!-- 공통 네비게이션 바 포함 -->
        <%@ include file="/WEB-INF/views/common/navbar.jsp" %>

        <main class="main-center">
            <div class="login-card">
            	<!-- 개인회원, 기업회원 로그인 탭 -->
                <div class="login-tabs">
                    <div class="login-tab active">개인 회원 로그인</div>
                    <div class="login-tab"><a href="/enterprises/login">기업 회원 로그인</a></div>
                </div>
                
				<!-- 로그인 처리 URL로 POST 전송 -->
                <form class="login-form" method="post" action="/members/loginProcess">
                    <div class="form-fields">
                    	<!-- 아이디, 비밀번호 입력 필드 -->
                        <input type="text" placeholder="아이디" name="username" />
                        <input type="password" placeholder="비밀번호" name="password" />
                    </div>
                    <!-- 로그인 버튼 -->
                    <button type="submit" class="login-button">로그인</button>
					<!-- CSRF 토큰 필드 (스프링 시큐리티 적용용) -->
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>
                
				<!-- 로그인 실패 시 오류 메시지 출력 -->
                <span class="error-msg"><c:out value="${error}" /></span>

                <div class="login-links">
                    <!-- 개인회원 가입 페이지 링크로 이동 -->
                    <a href="/members/signup">회원가입</a> <!-- 회원가입 페이지 링크로 이동 -->
                    
                </div>
            </div>
        </main>
    </div>
</body>
</html>


