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
    <!-- 이력서 등록 및 확인 버튼 CSS -->
    <link rel="stylesheet" href="/resources/css/resume.css">
	<!-- 구글 폰트 Inter 불러오기 -->
    <link href="https://fonts.googleapis.com/css?family=Inter:400,700&display=swap" rel="stylesheet">
    
</head>
<body>
    <div class="background">
   		<!-- 공통 네비게이션 바 포함 -->
        <%@ include file="/WEB-INF/views/common/navbar.jsp" %>
		<div class="resume-container">
        <!-- 제목 -->
        <div class="resume-title">
            <span>${resumes.title}</span>
        </div>

        <!-- 인적사항 -->
        <div class="personal-info">
            <span>인적사항</span>
        </div>
        <div class="form-section">
            <div class="form-group">
                <span class="personal-name">이상우</span>
            </div>
            <div class="form-group">
                <div class="info-item">
                    <ion-icon name="phone-portrait-outline"></ion-icon>
                    <span class="personal-phone">010-1234-1234</span>
                </div>
                <div class="info-item">
                    <ion-icon name="male-female-outline"></ion-icon>
                    <span class="personal-gender">남성</span>
                </div>
            </div>
            <div class="form-group">
                <div class="info-item">
                    <ion-icon name="home-outline"></ion-icon>
                    <span class="personal-address">대전 유성구 노은로 353</span>
                </div>
                <div class="info-item">
                    <ion-icon name="body-outline"></ion-icon>
                    <span class="personal-birth">1964 (62세)</span>
                </div>
            </div>
        </div>

        <!-- 최종학력 -->
        <div class="education">
            <span>최종학력</span>
        </div>
        <div class="form-section">
            <div class="form-group">
                <span>${resumes.schoolName} ${resumes.enterDate} ~ ${resumes.graduateDate} (${resumes.graduateStatus})</span>
            </div>
        </div>

        <!-- 경력사항 -->
        <div class="career">
            <span>경력사항 </span>
        </div>

        <!-- 자격사항 -->
        <div class="license">
            <span>자격사항</span>
        </div>

        <button class="modify-btn">수정하기</button>
    	</div>
    </div>
</body>
</html>
