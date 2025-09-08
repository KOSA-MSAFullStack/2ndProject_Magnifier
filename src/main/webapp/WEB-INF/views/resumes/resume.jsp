<!-- JSP 페이지 인코딩 및 컨텐츠 타입 설정 -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- JSTL core 라이브러리 선언 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- JSP 변수 path에 웹 애플리케이션 컨텍스트 경로 저장 -->
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>이력서</title>
    <!-- 브라우저 탭에 표시될 타이틀 -->
	<link rel="stylesheet" href="/resources/css/common.css">
    <!-- 이력서 관리 CSS -->
    <link rel="stylesheet" href="/resources/css/resume.css">
	<!-- 구글 폰트 Inter 불러오기 -->
    <link href="https://fonts.googleapis.com/css?family=Inter:400,700&display=swap" rel="stylesheet">
    
</head>
<body>
    <div class="background">
   		<!-- 공통 네비게이션 바 포함 -->
        <%@ include file="/WEB-INF/views/common/navbar.jsp" %>
		<!-- 
        <div class="resume-main">
	        <button class="resume-register" onclick="location.href='resume-form.html'">
				이력서 등록하기
	        </button>
	    </div>
	     -->
	    <div class="resume-main">
		    <c:if test="${hasResume}">
		        <button class="resume-btn" onclick="location.href='view';">이력서 확인하기</button>
		    </c:if>
		    <c:if test="${!hasResume}">
		        <button class="resume-btn" onclick="location.href='create';">이력서 등록하기</button>
		    </c:if>
	    </div>
    </div>
</body>
</html>
