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

        <h1>메인페이지</h1>
    </div>
</body>
</html>


