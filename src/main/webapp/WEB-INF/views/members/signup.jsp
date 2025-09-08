<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <title>회원가입</title>
    <link rel="stylesheet" href="/resources/css/signup.css" />
    <link rel="stylesheet" href="/resources/css/common.css" />
    <!-- jquery 라이브러리 추가 -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <div class="container">
        <div class="background"></div>
        <!-- 공통 네비게이션 바 포함 -->
       	<%@ include file="/WEB-INF/views/common/navbar.jsp" %>
        <div class="page-title">회원가입</div>
        
        <!-- 회원가입 입력 폼 -->
        <form id="signupForm" class="signup-form">
            <div class="form-group name-group">
            
	            <!-- 이름 입력란 -->
                <input type="text" id="name" name="name" placeholder="이름" class="input-box medium" required/>
                
                <!-- 성별 선택 라디오 버튼 -->
                <div class="gender-options">
                    <div class="radio-box">
                        <input type="radio" id="male" name="gender" value="M"/>
                        <label for="male">남</label>
                    </div>
                    <div class="radio-box">
                        <input type="radio" id="female" name="gender" value="F"/>
                        <label for="female">여</label>
                    </div>
                </div>
            </div>
            
            <!-- 아이디 입력 및 중복확인 버튼 -->
            <div class="form-group id-group">
                <input type="text" id="loginId" name="loginId" placeholder="아이디" class="input-box medium" required/>
                <button id="idCheck" type="button" class="btn-duplicate-check">중복 확인</button>
            </div>
            <div class="checkMsg"></div>
            
            <!-- 비밀번호 및 비밀번호 확인 입력란 -->
            <div class="form-group password-group">
                <input type="password" id="password" name="password" placeholder="비밀번호 (최소  4자, 최대  12자를 입력하세요.)" class="input-box large" minlength="4" maxlength="12" required/>
            </div>
            <div class="form-group password-confirm-group">
                <input type="password" id="passwordCheck" name="passwordCheck" placeholder="비밀번호 확인" class="input-box large" minlength="4" maxlength="12" required/>
            </div>
            
            <!-- 휴대폰 번호 입력란 -->
            <div class="form-group phone-group">
                <input type="text" id="phoneNumber" name="phoneNumber" placeholder="휴대폰 번호 (번호만 작성해주세요. 예시:01012345678 )" class="input-box large" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*?)\..*/g, '$1');" required/>
            </div>
            
            <!-- 생년월일 선택 박스: 년, 월, 일 -->
            <div class="form-group birth-group">
                <select id="year" class="select-box year" name="year"><option selected disabled>생년월일(년도)</option></select>
                <select id="month" class="select-box month" name="month"><option selected disabled>생년월일(월)</option></select>
                <select id="day" class="select-box day" name="day"><option selected disabled>생년월일(일)</option></select>
            </div>
            
             <!-- 우편번호 입력 및 우편번호 찾기 버튼 -->
            <div class="form-group postal-group">
                <input type="text" id="postNumber" name="postNumber" placeholder="우편번호" class="input-box medium" required/>
                <button type="button" class="btn-postal-search">우편번호 찾기</button>
            </div>
            
            <div class="form-group">
                <input type="text" id="address" name="address" placeholder="주소" class="input-box" required/>
            </div>

            <div class="form-group address-group">
                <input type="text" id="addressDetail" name="addressDetail" placeholder="상세 주소" class="input-box" required/>
                <input type="text" id="reference" name="reference" placeholder="참고 항목" class="input-box" required/>
            </div>
            
            <!-- 회원가입 제출 버튼 -->
            <button type="submit" class="btn-submit">가입하기</button>
            
            <!-- CSRF 토큰 히든 필드 (스프링 시큐리티용) -->
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </div>
    <script>
      //== 생년월일 selectBox 초기화 ==//
      // 연도(60세 이상으로 설정)
	  const yearSelect = document.getElementById("year");
	  const currentYear = new Date().getFullYear(); // 현재 연도
	  const minYear = currentYear - 60; // 60세 이상을 위한 최소 연도
	  for (var year = minYear; year >= 1900; year--) { // minYear부터 1900년까지 차례로 option 추가
		const option = document.createElement("option"); // 옵션 요소 생성
	    option.value = year; // value 속성 설정
	    option.text = year;  // 표시할 text 설정
	    yearSelect.appendChild(option); // selectBox에 옵션 추가
	  }
	  
	  // 월(1월 ~ 12월)
	  const monthSelect = document.getElementById("month");
	  for (var month = 1; month <= 12; month++) {
	    var option = document.createElement("option");
	    option.value = month;
	    option.text = month;
	    monthSelect.appendChild(option);
	  }

	  // 일(1일 ~ 31일)
	  const daySelect = document.getElementById("day");
	  for (var day = 1; day <= 31; day++) {
		const option = document.createElement("option");
	    option.value = day;
	    option.text = day;
	    daySelect.appendChild(option);
	  }
	  
	  //== POST 요청 : 아이디 중복확인 ==//
	  let checkId = 0;
	  $('#idCheck').on('click', function(event) {
		// 중복확인 결과 메세지 동적할당, 있으면 텍스트만 변경
        if ($('#loginId').val().trim() === '') {
          if ($('#messageId').length === 0) {
        	  $('.checkMsg').css({ 'margin-bottom': '30px' }).append('<span id="messageId" class="error-msg">아이디를 입력해주세요.</span>');
          } else {
            $('#messageId').text('아이디를 입력해주세요.')
                           .removeClass('success-msg')
                           .addClass('error-msg');
          }
          return; // AJAX 요청 중단
	    }
	    
	 	// loginId를 JSON 객체로 생성
	    const loginData = {
   		  "loginId": $('#loginId').val()
	    };
	 
	    $.ajax({
		      url: '/idCheck',  // 회원가입 처리 컨트롤러 URL
		      type: 'POST',
		      contentType: 'application/json', // JSON 형식으로 전송
		      data: JSON.stringify(loginData),  // JSON 문자열로 변환 후 전송
		      headers: {
		        'X-CSRF-TOKEN': '${_csrf.token}', // 스프링 시큐리티 사용
	        	'Accept': 'application/json'  
		      },
		      success: function(response) {
		    	// 중복확인 결과 메세지 동적할당
	            if ($('#messageId').length === 0) {
	            	$('.checkMsg').css({ 'margin-bottom': '30px' }).append('<span id="messageId"></span>');
	            }
	    	    if (response === true) {
	    	        $('#messageId').text('존재하는 아이디입니다.')
	    	        .removeClass('success-msg')  
	    	        .addClass('error-msg');      
	    	        checkId = 0;
	    	    } else if (response === false) {
	    	        $('#messageId').text('사용 가능한 아이디입니다.')
	    	        .removeClass('error-msg')   
	    	        .addClass('success-msg');   
	    	        checkId = 1;
	    	    } else {
	    	        checkId = 0;
	    	        alert('아이디 중복체크 오류');
	    	    }
	    	  },
		      error: function(xhr, status, error) {
		        alert('중복확인에 실패했습니다.');
		        console.error(error);
		      }
	    });
	  });
	  
	  //== POST 요청 : 회원가입 폼 제출 시 AJAX 처리 ==//
	  $('#signupForm').on('submit', function(event) {
	    event.preventDefault(); // 폼 기본 제출 차단
	    
	 	// 성별 선택 검사
	    if (!$('input[name="gender"]:checked').val()) {
	    	checkId = 0; // 중복확인 다시 하기 위하여 0 부여
	        alert('성별을 선택해주세요.');
	        return false;  // 폼 제출 중단
	    }

	    // 생년월일 검사
	    if (!$('#year').val() || !$('#month').val() || !$('#day').val()) {
	    	checkId = 0; // 중복확인 다시 하기 위하여 0 부여
	        alert('생년월일을 선택해주세요.');
	        return false;  // 폼 제출 중단
	    }
	    
	    // 아이디 중복확인 체크
	    if (checkId === 0) {
	        alert('아이디 중복 확인을 해주세요.');
	        return false; // 회원가입 진행 중단
	    }
	
	    // 폼 데이터 JSON 객체로 생성
	    const formData = {
   		  "name": $('#name').val(),
  	      "gender": $('input[name="gender"]:checked').val(),
  	      "loginId": $('#loginId').val(),
    	  "password": $('#password').val(),
   		  "passwordConfirm": $('#passwordConfirm').val(),
   		  "phoneNumber": $('#phoneNumber').val(),
   		  "year": parseInt($('#year').val(), 10),
   	      "month": parseInt($('#month').val(), 10),
   	      "day": parseInt($('#day').val(), 10),
	      "postNumber": $('#postNumber').val(),
	      "address": $('#address').val(),
	      "addressDetail": $('#addressDetail').val(),
	      "reference": $('#reference').val()
	    };

		// AJAX POST 요청
	    $.ajax({
	      url: '/members/signup',  // 회원가입 처리 컨트롤러 URL
	      type: 'POST',
	      contentType: 'application/json', // JSON 형식으로 전송
	      data: JSON.stringify(formData),  // JSON 문자열로 변환 후 전송
	      headers: {
	        'X-CSRF-TOKEN': '${_csrf.token}', // 스프링 시큐리티 사용
        	'Accept': 'application/json'  
	      },
	      success: function(response) {
	        alert('회원가입이 완료되었습니다.');
	        window.location.href = '/members/login'; // 가입 완료 후 로그인 페이지로 이동
          },
	      error: function(xhr, status, error) {
    	    checkId = 0; // 중복확인 다시 하기 위하여 0 부여
	        alert('회원가입에 실패했습니다.');
	        console.error(error);
	      }
	    });
	  });
</script>
</body>
</html>