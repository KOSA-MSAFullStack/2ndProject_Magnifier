<!-- JSP 페이지 인코딩 및 컨텐츠 타입 설정 -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- JSTL core 라이브러리 선언 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link
	href="https://fonts.googleapis.com/css?family=Inter:400,700&display=swap"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- Flatpickr 기본 CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<!-- MonthSelect 플러그인 CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/flatpickr/dist/plugins/monthSelect/style.css">
<style>
/* Flatpickr 달력 컨테이너의 글꼴 크기 키우기 */
.flatpickr-calendar {
	width: 18%;
	height: 25%;
	font-size: 1.35em;
}
</style>
</head>
<body>
	<div class="background">
		<!-- 공통 네비게이션 바 포함 -->
		<%@ include file="/WEB-INF/views/common/navbar.jsp"%>
		<div class="resume-container">
			<!-- 제목 -->
			<div class="form-section">
				<input class="resume-title" type="text" name="title"
					placeholder="제목" value="${resumes.title}">
			</div>

			<!-- 인적사항 -->
			<div class="personal-info">
				<span>인적사항</span>
			</div>
			<div class="form-section">
				<div class="form-group">
					<span class="personal-name">${member.name}</span>
				</div>
				<div class="form-group">
					<div class="info-item">
						<ion-icon name="phone-portrait-outline"></ion-icon>
						<span class="personal-phone">
							${fn:substring(member.phoneNumber, 0, 3)}-
						    ${fn:substring(member.phoneNumber, 3, 7)}-
						    ${fn:substring(member.phoneNumber, 7, 11)}
						</span>
					</div>
					<div class="info-item">
						<%
				            // Model에서 resumes 객체를 가져옵니다.
				            com.magnifier.member.dto.FindMemberResponse member = (com.magnifier.member.dto.FindMemberResponse) request.getAttribute("member");
				
				            // 객체가 null이 아닌지 확인합니다.
				            if (member != null && member.getGender() != null) {
				                // Member 객체에서 gender 값을 가져와 비교합니다.
				                char gender = member.getGender();
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
						<span class="personal-address">(${member.postNumber}) ${member.address} ${member.addressDetail} ${member.reference}</span>
					</div>
					<div class="info-item">
						<ion-icon name="body-outline"></ion-icon>
						<span class="personal-birth">${member.year}-${member.month}-${member.day}</span>
					</div>
				</div>
			</div>

			<!-- 최종학력 -->
			<div class="education">
				<span>최종학력</span>
			</div>
			<div class="form-section">
				<div class="form-group">
					<select class="school-type" name="schoolType">
						<option>학교 구분</option>
						<option value="초등학교" ${resumes.schoolType eq '초등학교' ? 'selected' : ''}>초등학교</option>
			            <option value="중학교" ${resumes.schoolType eq '중학교' ? 'selected' : ''}>중학교</option>
			            <option value="고등학교" ${resumes.schoolType eq '고등학교' ? 'selected' : ''}>고등학교</option>
			            <option value="대학교" ${resumes.schoolType eq '대학교' ? 'selected' : ''}>대학교</option>
			            <option value="대학원" ${resumes.schoolType eq '대학원' ? 'selected' : ''}>대학원</option>
			        </select>
				</div>
				<div class="form-group">
					<input class="school-name" type="text" name="schoolName"
						placeholder="학교명" value="${resumes.schoolName}"> 
						<select class="graduate-status" name="graduateStatus">
						<option>졸업여부</option>
						<option value="졸업" ${resumes.graduateStatus eq '졸업' ? 'selected' : ''}>졸업</option>
			            <option value="중퇴" ${resumes.graduateStatus eq '중퇴' ? 'selected' : ''}>중퇴</option>
			        </select> 
			        <input class="enter-date" type="text" id="admissionMonth" name="enterDate" placeholder="입학년월" value="${resumes.enterDate}"> 
			        <input class="graduate-date" type="text" id="graduationMonth" name="graduateDate" placeholder="졸업년월" value="${resumes.graduateDate}">
				</div>
			</div>

			<!-- 경력사항 -->
			<div class="career">
				<span>경력사항
					<button class="add-btn">
						<ion-icon name="add-outline"></ion-icon>
						경력 추가
					</button>
				</span>
			</div>
			<c:forEach items="${resumes.careerList}" var="career">
			    <div class="saved-career-info" data-id="${career.careerId}">
			        <hr>
			        <p>회사명: ${career.name}</p>
			        <p>근무기간: ${fn:substring(fn:replace(career.joinDate, '-', '.'), 0, 7)} ~ ${fn:substring(fn:replace(career.quitDate, '-', '.'), 0, 7)}</p>
			        <p>직무: ${career.job}</p>
			        <p>근무부서: ${career.department}</p>
			        <p>직급: ${career.position}</p>
			        <div class="actions">
			            <button type="button" class="edit-btn">수정</button>
			            <button type="button" class="delete-btn">삭제</button>
			        </div>
			    </div>
			</c:forEach>

			<!-- 자격사항 -->
			<div class="license">
				<span>자격사항
					<button class="add-btn">
						<ion-icon name="add-outline"></ion-icon>
						자격 추가
					</button>
				</span>
			</div>
			<c:forEach items="${resumes.licenseList}" var="license">
			    <div class="saved-license-info" data-id="${license.licenseId}">
			        <hr>
			        <p>자격증명: ${license.name}</p>
			        <p>발행처/기관: ${license.publisher}</p>
			        <p>취득일자: ${fn:substring(fn:replace(license.passDate, '-', '.'), 0, 7)}</p>
			        <div class="actions">
			            <button type="button" class="edit-btn">수정</button>
			            <button type="button" class="delete-btn">삭제</button>
			        </div>
			    </div>
			</c:forEach>

			<c:choose>
			    <c:when test="${not empty resumes.resumeId}">
			        <button class="update-btn">수정하기</button>
			        <input type="hidden" name="resumeId" value="${resumes.resumeId}" />
			    </c:when>
			    <c:otherwise>
			        <button class="submit-btn">저장하기</button>
			    </c:otherwise>
			</c:choose>
			<!-- CSRF 토큰 히든 필드 (스프링 시큐리티용) -->
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</div>
	</div>
</body>
<!-- Ionicons CDN -->
<script type="module"
	src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule
	src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
<!-- Flatpickr JS -->
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<!-- MonthSelect 플러그인 JS -->
<script
	src="https://cdn.jsdelivr.net/npm/flatpickr/dist/plugins/monthSelect/index.js"></script>
<!-- 한국어 로케일 추가 -->
<script src="https://cdn.jsdelivr.net/npm/flatpickr/dist/l10n/ko.js"></script>

<script>
    // 공통 옵션
    const monthPickerOptions = {
        locale: "ko",  // 한국어 적용
        plugins: [
            new monthSelectPlugin({
                shorthand: true,      // "9월" 같은 짧은 표기
                dateFormat: "Y.m",     // 실제 값: 2025.09
                altFormat: "Y년 m월", // 화면 표시: 2025년 9월
                theme: "light"        // 테마: "light" or "dark"
            })
        ]
    };

    // 적용
    flatpickr("#admissionMonth", monthPickerOptions);
    flatpickr("#graduationMonth", monthPickerOptions);

    // 1. "경력 추가" 버튼과 폼이 추가될 부모 요소를 선택합니다.
    const careerAddBtn = document.querySelector('.career .add-btn');
    const careerHeader = document.querySelector('.career');

    // 2. 추가할 폼 섹션의 HTML 구조를 정의합니다.
    const newFormTemplate = `
        <div class="form-section">
            <div class="form-group">
                <input type="text" placeholder="회사명">
                <input class="join-date" type="text" placeholder="입사년월">
                <input class="quit-date" type="text" placeholder="퇴사년월">
            </div>
            <div class="form-group">
                <input type="text" placeholder="직무">
                <input type="text" placeholder="근무부서">
                <input type="text" placeholder="직급/직책">
            </div>
            <div class="button-group">
                <button class="cancel-btn">취소</button>
                <button class="save-btn">저장</button>
            </div>
        </div>
    `;

    careerAddBtn.addEventListener('click', () => {
        careerAddBtn.classList.add('hidden');

        const newFormContainer = document.createElement('div');
        newFormContainer.innerHTML = newFormTemplate;

        careerHeader.after(newFormContainer);

        const cancelBtn = newFormContainer.querySelector('.cancel-btn');
        const saveBtn = newFormContainer.querySelector('.save-btn');

        // 새로 추가된 폼의 입력 필드들을 선택
        const inputs = newFormContainer.querySelectorAll('input');

        // 새로 생성된 폼의 '입사년월'과 '퇴사년월' 입력 필드에 flatpickr를 적용
        const newJoinMonthInput = newFormContainer.querySelector('.join-date');
        const newQuitMonthInput = newFormContainer.querySelector('.quit-date');

        // 중요한 수정: 동적으로 생성된 요소에 flatpickr 적용
        flatpickr(newJoinMonthInput, monthPickerOptions);
        flatpickr(newQuitMonthInput, monthPickerOptions);

        // '취소' 버튼 클릭 시
        cancelBtn.addEventListener('click', () => {
            careerAddBtn.classList.remove('hidden');
            newFormContainer.remove();
        });

        // '저장' 버튼 클릭 시
        saveBtn.addEventListener('click', () => {
            // 1. 모든 입력 필드 값을 배열로 가져옴
            const inputValues = Array.from(inputs).map(input => input.value.trim());

            // 2. 모든 입력 필드가 채워졌는지 확인
            const allFieldsFilled = inputValues.every(value => value !== '');

            if (!allFieldsFilled) {
                alert('모든 입력란을 채워주세요.');
                return; // 함수 실행을 여기서 중단
            }

            // 3. 입력 필드의 값을 가져와서 변수에 저장
            const name = inputs[0].value;
            const joinDate = inputs[1].value;
            const quitDate = inputs[2].value;
            const job = inputs[3].value;
            const department = inputs[4].value;
            const position = inputs[5].value;

            // 입력된 정보로 새로운 HTML 구조를 만듬
            const savedInfoTemplate = 
            	'<div class="saved-career-info">' +
			    '<hr>' +
			    '<p>회사명: ' + name + '</p>' +
			    '<p>근무기간: ' + joinDate + ' ~ ' + quitDate + '</p>' +
			    '<p>직무: ' + job + '</p>' +
			    '<p>근무부서: ' + department + '</p>' +
			    '<p>직급: ' + position + '</p>' +
			    '<div class="actions">' +
		            '<button type="button" class="edit-btn">수정</button>' +
		            '<button type="button" class="delete-btn">삭제</button>' +
		        '</div>' +
			    '</div>';
            // 새로운 요소를 생성하고, 템플릿 내용을 추가
            const savedInfoContainer = document.createElement('div');
            savedInfoContainer.innerHTML = savedInfoTemplate;

            // 기존 폼 섹션을 제거하고, 그 자리에 저장된 정보를 삽입
            newFormContainer.remove();
            careerHeader.after(savedInfoContainer);

            // '경력 추가' 버튼을 다시 표시
            careerAddBtn.classList.remove('hidden');
            
            const savedCareerDiv = savedInfoContainer.querySelector('.saved-career-info');
            attachActionListeners(savedCareerDiv);
        });
    });

    // 자격사항 추가 기능
    // 1. 자격사항 관련 버튼과 부모 요소를 선택
    const licenseAddBtn = document.querySelector('.license .add-btn');
    const licenseHeader = document.querySelector('.license');

    // 2. 추가할 자격사항 폼의 HTML 구조를 정의
    const newLicenseFormTemplate = `
        <div class="form-section">
            <div class="form-group">
                <input type="text" placeholder="자격증명">
                <input type="text" placeholder="발행처/기관">
                <input class="pass-date" type="text" placeholder="취득일자">
            </div>
            <div class="button-group">
                <button class="cancel-btn">취소</button>
                <button class="save-btn">저장</button>
            </div>
        </div>
    `;

    // 3. '자격 추가' 버튼에 클릭 이벤트 리스너를 추가
    licenseAddBtn.addEventListener('click', () => {
        // 3-1. '자격 추가' 버튼을 숨김
        licenseAddBtn.classList.add('hidden');

        // 3-2. 새로운 폼 요소를 생성
        const newFormContainer = document.createElement('div');
        newFormContainer.innerHTML = newLicenseFormTemplate;

        // 3-3. 생성된 폼을 '자격사항' 헤더 뒤에 삽입
        licenseHeader.after(newFormContainer);

        // 3-4. 새로 추가된 폼의 버튼들과 입력 필드를 선택
        const cancelBtn = newFormContainer.querySelector('.cancel-btn');
        const saveBtn = newFormContainer.querySelector('.save-btn');
        const inputs = newFormContainer.querySelectorAll('input');

        // 3-5. '취득일자' 입력 필드에 flatpickr를 적용
        const newPassMonthInput = newFormContainer.querySelector('.pass-date');
        flatpickr(newPassMonthInput, monthPickerOptions);

        // '취소' 버튼 클릭 시
        cancelBtn.addEventListener('click', () => {
            licenseAddBtn.classList.remove('hidden');
            newFormContainer.remove();
        });

        // '저장' 버튼 클릭 시
        saveBtn.addEventListener('click', () => {
            // 1. 입력 필드 유효성 검사
            const inputValues = Array.from(inputs).map(input => input.value.trim());
            const allFieldsFilled = inputValues.every(value => value !== '');
            
            if (!allFieldsFilled) {
                alert('모든 입력란을 채워주세요.');
                return;
            }

            // 2. 입력 값을 가져와서 변수에 저장
            const name = inputs[0].value;
            const publisher = inputs[1].value;
            const passDate = inputs[2].value;

            // 3. 입력된 정보로 새로운 HTML 구조를 만듬
            const savedInfoTemplate = '<hr>' +
			    '<div class="saved-license-info">' +
			    '<p>자격증명: ' + name + '</p>' +
			    '<p>발행처/기관: ' + publisher + '</p>' +
			    '<p>취득일자: ' + passDate + '</p>' +
			    '<div class="actions">' +
		            '<button type="button" class="edit-btn">수정</button>' +
		            '<button type="button" class="delete-btn">삭제</button>' +
		        '</div>' +
			    '</div>';

            // 4. 새로운 요소를 생성하고, 그 자리에 저장된 정보를 삽입
            const savedInfoContainer = document.createElement('div');
            savedInfoContainer.innerHTML = savedInfoTemplate;

            newFormContainer.remove();
            licenseHeader.after(savedInfoContainer);

            // 5. '자격 추가' 버튼을 다시 표시
            licenseAddBtn.classList.remove('hidden');
            
            attachActionListeners(savedInfoContainer);
        });
    });
    
 	// '수정' 및 '삭제' 버튼에 이벤트 리스너를 동적으로 추가하는 함수
    function attachActionListeners(container) {
	    const editBtn = container.querySelector('.edit-btn');
	    const deleteBtn = container.querySelector('.delete-btn');
	
	    // 삭제 버튼 클릭 이벤트
	    deleteBtn.addEventListener('click', () => {
	        if (confirm('정말 이 항목을 삭제하시겠습니까?')) {
	            container.remove();
	        }
	    });
	
	    // 수정 버튼 클릭 이벤트
	    editBtn.addEventListener('click', () => {
	        const isCareer = container.classList.contains('saved-career-info');
	        const savedData = {};
			
	        if (isCareer) {
	            // 경력 데이터 추출
	            savedData.name = container.querySelector('p:nth-of-type(1)').textContent.replace('회사명: ', '').trim();
	            const period = container.querySelector('p:nth-of-type(2)').textContent.replace('근무기간: ', '').trim().split('~');
	            savedData.joinDate = period[0].trim();
	            savedData.quitDate = period[1].trim();
	            savedData.job = container.querySelector('p:nth-of-type(3)').textContent.replace('직무: ', '').trim();
	            savedData.department = container.querySelector('p:nth-of-type(4)').textContent.replace('근무부서: ', '').trim();
	            savedData.position = container.querySelector('p:nth-of-type(5)').textContent.replace('직급: ', '').trim();
	            
	            replaceWithForm(container, createCareerFormTemplate(savedData), 'career');
	        } else {
	            // 자격증 데이터 추출
	            savedData.name = container.querySelector('p:nth-of-type(1)').textContent.replace('자격증명: ', '').trim();
	            savedData.publisher = container.querySelector('p:nth-of-type(2)').textContent.replace('발행처/기관: ', '').trim();
	            savedData.passDate = container.querySelector('p:nth-of-type(3)').textContent.replace('취득일자: ', '').trim();
	            
	            replaceWithForm(container, createLicenseFormTemplate(savedData), 'license');
	        }
	    });
	}
    
 	// 폼 템플릿 생성 함수 (경력)
    function createCareerFormTemplate(data = {}) {
    	return '<div class="edit-form-section">'
        + ' <div class="form-group">'
        + ' <input type="text" placeholder="회사명" value="' + data.name + '">'
        + ' <input class="join-date" type="text" placeholder="입사년월" value="' + data.joinDate + '">'
        + ' <input class="quit-date" type="text" placeholder="퇴사년월" value="' + data.quitDate + '">'
        + ' </div>'
        + ' <div class="form-group">'
        + ' <input type="text" placeholder="직무" value="' + data.job + '">'
        + ' <input type="text" placeholder="근무부서" value="' + data.department + '">'
        + ' <input type="text" placeholder="직급/직책" value="' + data.position + '">'
        + ' </div>'
        + ' <div class="button-group">'
        + ' <button type="button" class="cancel-btn">취소</button>'
        + ' <button type="button" class="save-btn">저장</button>'
        + ' </div>'
        + ' </div>';
	}

    // 폼 템플릿 생성 함수 (자격)
    function createLicenseFormTemplate(data = {}) {
    	return '<div class="edit-form-section">'
    	+ ' <div class="form-group">'
    	+ ' <input type="text" placeholder="자격증명" value="' + data.name + '">'
    	+ ' <input type="text" placeholder="발행처/기관" value="' + data.publisher + '">'
    	+ ' <input class="pass-date" type="text" placeholder="취득일자" value="' + data.passDate + '">'
    	+ ' </div>'
    	+ ' <div class="button-group">'
    	+ ' <button type="button" class="cancel-btn">취소</button>'
    	+ ' <button type="button" class="save-btn">저장</button>'
    	+ ' </div>'
    	+ ' </div>';
	}
    
 	// 기존 정보를 폼으로 교체하고 이벤트 리스너를 부착하는 함수
    function replaceWithForm(savedContainer, formTemplate, type) {
	    const formContainer = document.createElement('div');
	    formContainer.innerHTML = formTemplate;
	
	    // 기존 컨테이너를 숨기고 그 자리에 새 폼을 삽입
	    savedContainer.style.display = 'none'; // hidden 클래스 대신 직접 스타일 적용
	    savedContainer.after(formContainer);
	
	    const inputs = formContainer.querySelectorAll('input');
	    const cancelBtn = formContainer.querySelector('.cancel-btn');
	    const saveBtn = formContainer.querySelector('.save-btn');
	    
	    // Flatpickr 재적용
	    if (type === 'career') {
	        flatpickr(formContainer.querySelector('.join-date'), monthPickerOptions);
	        flatpickr(formContainer.querySelector('.quit-date'), monthPickerOptions);
	    } else if (type === 'license') {
	        flatpickr(formContainer.querySelector('.pass-date'), monthPickerOptions);
	    }
	
	    // '취소' 버튼 클릭 시
	    cancelBtn.addEventListener('click', () => {
	        formContainer.remove();
	        savedContainer.style.display = ''; // 다시 보이게
	    });
	
	    // '저장' 버튼 클릭 시
	    saveBtn.addEventListener('click', () => {
	        const newValues = Array.from(inputs).map(input => input.value.trim());
	
	        if (newValues.some(value => value === '')) {
	            alert('모든 입력란을 채워주세요.');
	            return;
	        }
	
	        // 업데이트된 값으로 기존 HTML 업데이트
	        if (type === 'career') {
			    savedContainer.querySelector('p:nth-of-type(1)').textContent = '회사명: ' + newValues[0];
			    savedContainer.querySelector('p:nth-of-type(2)').textContent = '근무기간: ' + newValues[1] + ' ~ ' + newValues[2];
			    savedContainer.querySelector('p:nth-of-type(3)').textContent = '직무: ' + newValues[3];
			    savedContainer.querySelector('p:nth-of-type(4)').textContent = '근무부서: ' + newValues[4];
			    savedContainer.querySelector('p:nth-of-type(5)').textContent = '직급: ' + newValues[5];
			} else if (type === 'license') {
			    savedContainer.querySelector('p:nth-of-type(1)').textContent = '자격증명: ' + newValues[0];
			    savedContainer.querySelector('p:nth-of-type(2)').textContent = '발행처/기관: ' + newValues[1];
			    savedContainer.querySelector('p:nth-of-type(3)').textContent = '취득일자: ' + newValues[2];
			}
	        
	        formContainer.remove();
	        savedContainer.style.display = ''; // 다시 보이게
	    });
	}
 
 	// 페이지 로드 시 기존 항목들에 이벤트 리스너 부착
    document.querySelectorAll('.saved-career-info').forEach(container => {
        attachActionListeners(container);
    });

    document.querySelectorAll('.saved-license-info').forEach(container => {
        attachActionListeners(container);
    });
 	
 	// 모든 입력 필드의 데이터를 수집하는 함수
    function collectAllFormData() {
        const resumeData = {};
        
     	// 0. 기본 입력 필드 데이터 수집
        resumeData.title = document.querySelector('input[name="title"]').value;

        // 1. 학력 데이터 수집
        resumeData.schoolType = document.querySelector('select[name="schoolType"]').value;
        resumeData.schoolName = document.querySelector('input[name="schoolName"]').value;
        resumeData.graduateStatus = document.querySelector('select[name="graduateStatus"]').value;
        resumeData.enterDate = document.querySelector('input[name="enterDate"]').value + '.01';
        resumeData.graduateDate = document.querySelector('input[name="graduateDate"]').value + '.01';

   	  	// 2. 동적으로 추가된 경력사항 데이터 수집
        resumeData.careerList = [];
        document.querySelectorAll('.saved-career-info').forEach(careerDiv => {
            const career = {};
            const careerId = careerDiv.getAttribute('data-id');
            if (careerId) {
                career.careerId = careerId;
            }
            career.name = careerDiv.querySelector('p:nth-of-type(1)').textContent.replace('회사명: ', '');
         	// 근무기간 문자열을 가져와서 분리
            const periodString = careerDiv.querySelector('p:nth-of-type(2)').textContent.replace('근무기간: ', '').trim();
            const dates = periodString.split('~');
            career.joinDate = dates[0].trim() + '.01';
            career.quitDate = dates[1].trim() + '.01';
            
            career.job = careerDiv.querySelector('p:nth-of-type(3)').textContent.replace('직무: ', '');
            career.department = careerDiv.querySelector('p:nth-of-type(4)').textContent.replace('근무부서: ', '');
            career.position = careerDiv.querySelector('p:nth-of-type(5)').textContent.replace('직급: ', '');
            resumeData.careerList.push(career);
        });

        // 3. 동적으로 추가된 자격사항 데이터 수집
        resumeData.licenseList = [];
        document.querySelectorAll('.saved-license-info').forEach(licenseDiv => {
            const license = {};
            const licenseId = licenseDiv.getAttribute('data-id');
            if (licenseId) {
            	license.licenseId = licenseId;
            }
            license.name = licenseDiv.querySelector('p:nth-of-type(1)').textContent.replace('자격증명: ', '');
            license.publisher = licenseDiv.querySelector('p:nth-of-type(2)').textContent.replace('발행처/기관: ', '');
            license.passDate = licenseDiv.querySelector('p:nth-of-type(3)').textContent.replace('취득일자: ', '') + '.01';
            resumeData.licenseList.push(license);
        });

        return resumeData;
    }
 	
 	// 모든 필수 항목을 검증하는 함수
    function validateResumeForm() {
        // .trim()을 사용하여 앞뒤 공백을 제거하고 값이 비었는지 확인
        const title = document.querySelector('input[name="title"]').value.trim();
        // select 태그는 value가 '학교 구분' 또는 '졸업여부'인 경우를 확인
        const schoolType = document.querySelector('select[name="schoolType"]').value;
        const schoolName = document.querySelector('input[name="schoolName"]').value.trim();
        const graduateStatus = document.querySelector('select[name="graduateStatus"]').value;
        const enterDate = document.querySelector('input[name="enterDate"]').value.trim();
        const graduateDate = document.querySelector('input[name="graduateDate"]').value.trim();

        // 필수 항목 중 하나라도 비어있으면 false 반환
        if (
            title === '' ||
            schoolType === '학교 구분' || 
            schoolName === '' ||
            graduateStatus === '졸업여부' ||
            enterDate === '' ||
            graduateDate === ''
        ) {
            return false; // 유효성 검사 실패
        }
        return true; // 모든 필수 항목이 채워짐
    }

    // 저장 버튼 클릭 시 서버에 데이터 전송
    const submitBtn = document.querySelector('.submit-btn');
	if (submitBtn) {
	    submitBtn.addEventListener('click', () => {
	        if (!validateResumeForm()) {
	            alert('제목과 최종 학력의 모든 필수 항목을 채워주세요.');
	            return;
	        }
	
	        const data = collectAllFormData();
	        $.ajax({
	            url: '/resumes/register',
	            type: 'POST',
	            contentType: 'application/json',
	            data: JSON.stringify(data),
	            headers: {
	                'X-CSRF-TOKEN': '${_csrf.token}',
	                'Accept': 'application/json'
	            },
	            success: function(result) {
	                if (result.success) {
	                    alert('이력서가 성공적으로 등록되었습니다.');
	                    window.location.href = "${path}/resumes/"; 
	                } else {
	                    alert('등록 실패: ' + result.message);
	                }
	            },
	            error: function(xhr, status, error) {
	                console.error('Error:', error);
	                console.error('Status:', status);
	                console.error('Response:', xhr.responseText);
	                alert('서버와 통신 중 오류가 발생했습니다.');
	            }
	        });
	    });
	}
    
 	// 수정 버튼 클릭 시 서버에 데이터 전송
 	const updateBtn = document.querySelector('.update-btn');
	if (updateBtn) {
	    document.querySelector('.update-btn').addEventListener('click', () => {
	        if (!validateResumeForm()) {
	            alert('제목과 최종 학력의 모든 필수 항목을 채워주세요.');
	            return;
	        }
	        
	        const data = collectAllFormData();
	        const resumeId = document.querySelector('input[name="resumeId"]').value;
	        data.resumeId = resumeId; // 서버에 보낼 데이터에 resumeId 추가
	        
	        
	        $.ajax({
	            url: '/resumes/' + resumeId, // 수정 전용 URL (ID를 포함)
	            type: 'PUT', // 수정 요청에 맞는 HTTP 메서드
	            contentType: 'application/json',
	            data: JSON.stringify(data),
	            headers: {
	            	'X-CSRF-TOKEN': '${_csrf.token}',
	            	'Accept': 'application/json'
	            },
	            success: function(result) {
	                if (result.success) {
	                    alert('이력서가 성공적으로 수정되었습니다.');
	                    window.location.href = "${path}/resumes/view"; 
	                } else {
	                    alert('수정 실패: ' + result.message);
	                }
	            },
	            error: function(xhr, status, error) {
	                console.error('Error:', error);
	                console.error('Status:', status);
	                console.error('Response:', xhr.responseText);
	                alert('서버와 통신 중 오류가 발생했습니다.');
	            }
	        });
	    });
	}
</script>
</html>
