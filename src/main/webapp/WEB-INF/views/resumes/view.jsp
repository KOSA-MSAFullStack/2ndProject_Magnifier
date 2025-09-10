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
                <span class="personal-name">${resumes.member.name}</span>
            </div>
            <div class="form-group">
                <div class="info-item">
                    <ion-icon name="phone-portrait-outline"></ion-icon>
                    <span class="personal-phone">
						${fn:substring(resumes.member.phoneNumber, 0, 3)}-
					    ${fn:substring(resumes.member.phoneNumber, 3, 7)}-
					    ${fn:substring(resumes.member.phoneNumber, 7, 11)}
					</span>
                </div>
                <div class="info-item">
                    <%
			            // Model에서 resumes 객체를 가져옵니다.
			            com.magnifier.resume.dto.ResumeDto resume = (com.magnifier.resume.dto.ResumeDto) request.getAttribute("resumes");
			
			            // 객체가 null이 아닌지 확인합니다.
			            if (resume != null && resume.getMember() != null) {
			                // Member 객체에서 gender 값을 가져와 비교합니다.
			                char gender = resume.getMember().getGender();
			                if (gender == 'M') {
			                	out.print("<ion-icon name='male-outline'></ion-icon>");
			                    out.print("<span class='personal-gender'>남성</span>");
			                } else if (gender == 'F') {
			                	out.print("<ion-icon name='female-outline'></ion-icon>");
			                    out.print("<span class='personal-gender'>여성</span>");
			                } else {
			                    out.print("성별 정보 없음");
			                }
			            }
			        %>
                </div>
            </div>
            <div class="form-group">
                <div class="info-item">
                    <ion-icon name="home-outline"></ion-icon>
                    <span class="personal-address">(${resumes.member.postNumber}) ${resumes.member.address} ${resumes.member.addressDetail} ${resumes.member.reference}</span>
                </div>
                <div class="info-item">
                    <ion-icon name="body-outline"></ion-icon>
                    <span class="personal-birth">${resumes.member.birth}</span>
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
            <c:forEach items="${resumes.careerList}" var="career">
            	<hr>
	            <div class="saved-career-info">
	                <p>회사명: ${career.name}</p>
	                <p>근무기간: ${career.joinDate} ~ ${career.quitDate}</p>
	                <p>직무: ${career.job}</p>
	                <p>근무부서: ${career.department}</p>
	                <p>직급: ${career.position}</p>
	            </div>
            </c:forEach>
        </div>

        <!-- 자격사항 -->
        <div class="license">
            <span>자격사항</span>
            <c:forEach items="${resumes.licenseList}" var="license">
            	<hr>
                <div class="saved-license-info">
                    <p>자격증명: ${license.name}</p>
                    <p>발행처/기관: ${license.publisher}</p>
                    <p>취득일자: ${license.passDate}</p>
                </div>
            </c:forEach>
        </div>

        <button class="modify-btn" onclick="location.href='edit';">수정하기</button>
    	</div>
    </div>
</body>
<!-- Ionicons CDN -->
<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
</html>
