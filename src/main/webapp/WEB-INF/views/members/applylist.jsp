<!-- author: 이상우 -->
<!-- JSP 페이지 인코딩 및 컨텐츠 타입 설정 -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- JSTL core 라이브러리 선언 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- JSTL functions 라이브러리 선언 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- JSP 변수 path에 웹 애플리케이션 컨텍스트 경로 저장 -->
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>지원 내역</title>
    <!-- 브라우저 탭에 표시될 타이틀 -->
	<link rel="stylesheet" href="/resources/css/common.css">
    <link rel="stylesheet" href="/resources/css/applylist.css" />
	<!-- 구글 폰트 Inter 불러오기 -->
    <link href="https://fonts.googleapis.com/css?family=Inter:400,700&display=swap" rel="stylesheet">
    
</head>
<body>
    <div class="background">
   		<!-- 공통 네비게이션 바 포함 -->
        <%@ include file="/WEB-INF/views/common/navbar.jsp" %>
		
		<div class="applylist-title">
			<span>지원 내역</span>
		</div>
		<c:forEach items="${applylist}" var="applylist">
		<div class="application-card" onclick="location.href='/recruits/detail/${applylist.recruitId}'">
            <span class="company-name">${applylist.name}</span>
            <span class="job-title">${applylist.title}</span>
            <div class="application-status">
                <span class="status-label">지원완료</span>
                <span class="application-date-info">${applylist.createdAt} 지원</span>
            </div>
        </div>
        </c:forEach>
    </div>
</body>
</html>
