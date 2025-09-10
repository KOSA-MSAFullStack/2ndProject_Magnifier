<!-- JSP 페이지 인코딩 및 컨텐츠 타입 설정 -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- JSTL core 라이브러리 선언 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					placeholder="제목">
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
					<select class="school-type" name="schoolType">
						<option>학교 구분</option>
						<option>고등학교</option>
						<option>대학교</option>
						<option>대학원</option>
					</select>
				</div>
				<div class="form-group">
					<input class="school-name" type="text" name="schoolName"
						placeholder="학교명"> <select class="graduate-status"
						name="graduateStatus">
						<option>졸업여부</option>
						<option>졸업</option>
						<option>중퇴</option>
					</select> <input class="enter-date" type="text" id="admissionMonth"
						name="enterDate" placeholder="입학년월"> <input
						class="graduate-date" type="text" id="graduationMonth"
						name="graduateDate" placeholder="졸업년월">
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

			<!-- 자격사항 -->
			<div class="license">
				<span>자격사항
					<button class="add-btn">
						<ion-icon name="add-outline"></ion-icon>
						자격 추가
					</button>
				</span>
			</div>

			<button class="submit-btn">저장하기</button>
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
            const savedInfoTemplate = '<hr>' +
			    '<div class="saved-career-info">' +
			    '<p>회사명: ' + name + '</p>' +
			    '<p>근무기간: ' + joinDate + ' ~ ' + quitDate + '</p>' +
			    '<p>직무: ' + job + '</p>' +
			    '<p>근무부서: ' + department + '</p>' +
			    '<p>직급: ' + position + '</p>' +
			    '</div>';
            // 새로운 요소를 생성하고, 템플릿 내용을 추가
            const savedInfoContainer = document.createElement('div');
            savedInfoContainer.innerHTML = savedInfoTemplate;

            // 기존 폼 섹션을 제거하고, 그 자리에 저장된 정보를 삽입
            newFormContainer.remove();
            careerHeader.after(savedInfoContainer);

            // '경력 추가' 버튼을 다시 표시
            careerAddBtn.classList.remove('hidden');
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
			    '</div>';

            // 4. 새로운 요소를 생성하고, 그 자리에 저장된 정보를 삽입
            const savedInfoContainer = document.createElement('div');
            savedInfoContainer.innerHTML = savedInfoTemplate;

            newFormContainer.remove();
            licenseHeader.after(savedInfoContainer);

            // 5. '자격 추가' 버튼을 다시 표시
            licenseAddBtn.classList.remove('hidden');
        });
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
            career.name = careerDiv.querySelector('p:nth-child(1)').textContent.replace('회사명: ', '');
         	// 근무기간 문자열을 가져와서 분리
            const periodString = careerDiv.querySelector('p:nth-child(2)').textContent.replace('근무기간: ', '').trim();
            const dates = periodString.split('~');
            career.joinDate = dates[0].trim() + '.01';
            career.quitDate = dates[1].trim() + '.01';
            
            career.job = careerDiv.querySelector('p:nth-child(3)').textContent.replace('직무: ', '');
            career.department = careerDiv.querySelector('p:nth-child(4)').textContent.replace('근무부서: ', '');
            career.position = careerDiv.querySelector('p:nth-child(5)').textContent.replace('직급: ', '');
            resumeData.careerList.push(career);
        });

        // 3. 동적으로 추가된 자격사항 데이터 수집
        resumeData.licenseList = [];
        document.querySelectorAll('.saved-license-info').forEach(licenseDiv => {
            const license = {};
            license.name = licenseDiv.querySelector('p:nth-child(1)').textContent.replace('자격증명: ', '');
            license.publisher = licenseDiv.querySelector('p:nth-child(2)').textContent.replace('발행처/기관: ', '');
            license.passDate = licenseDiv.querySelector('p:nth-child(3)').textContent.replace('취득일자: ', '') + '.01';
            resumeData.licenseList.push(license);
        });

        return resumeData;
    }

    // "저장하기" 버튼 클릭 시 서버에 데이터 전송
    document.querySelector('.submit-btn').addEventListener('click', () => {
        const data = collectAllFormData();
        console.log(data);
        $.ajax({
            url: '/members/resumes/register',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            headers: {
            	'X-CSRF-TOKEN': '${_csrf.token}',
            	'Accept': 'application/json'
            },
            success: function(result) {
                console.log("서버로부터 받은 응답 데이터:", result);
                if (result.success) {
                    alert('이력서가 성공적으로 등록되었습니다.');
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
</script>
</html>
