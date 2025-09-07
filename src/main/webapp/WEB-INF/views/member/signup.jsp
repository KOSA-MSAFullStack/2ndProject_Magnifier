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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <div class="container">
        <div class="background"></div>
       	<%@ include file="/WEB-INF/views/common/navbar.jsp" %>
        <div class="page-title">회원가입</div>
        
        <form id="signupForm" class="signup-form">
            <div class="form-group name-group">
                <input type="text" id="name" name="name" placeholder="이름" class="input-box medium" />
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
            <div class="form-group id-group">
                <input type="text" id="loginId" name="loginId" placeholder="아이디" class="input-box medium" />
                <button type="button" class="btn-duplicate-check">중복 확인</button>
            </div>
            <div class="form-group password-group">
                <input type="password" id="password" name="password" placeholder="비밀번호" class="input-box large" />
            </div>
            <div class="form-group password-confirm-group">
                <input type="password" id="passwordConfirm" name="passwordConfirm" placeholder="비밀번호 확인" class="input-box large" />
            </div>
            <div class="form-group phone-group">
                <input type="text" id="phoneNumber" name="phoneNumber" placeholder="휴대폰 번호 (번호만 작성해주세요. 예시:010XXXXXXXX )" class="input-box large" />
            </div>
            
            <div class="form-group birth-group">
                <select id="year" class="select-box year" name="year"><option selected disabled>생년월일(년도)</option></select>
                <select id="month" class="select-box month" name="month"><option selected disabled>생년월일(월)</option></select>
                <select id="day" class="select-box day" name="day"><option selected disabled>생년월일(일)</option></select>
            </div>
            
            <div class="form-group postal-group">
                <input type="text" id="postNumber" name="postNumber" placeholder="우편번호" class="input-box medium" />
                <button type="button" class="btn-postal-search">우편번호 찾기</button>
            </div>
            
            <div class="form-group">
                <input type="text" id="address" name="address" placeholder="주소" class="input-box" />
            </div>

            <div class="form-group address-group">
                <input type="text" id="addressDetail" name="addressDetail" placeholder="상세 주소" class="input-box" />
                <input type="text" id="reference" name="reference" placeholder="참고 항목" class="input-box" />
            </div>
            
            <button type="submit" class="btn-submit">가입하기</button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </div>
    <script>
      // 생년월일 selectBox 
      // 연도(60세 이상으로 설정)
	  var yearSelect = document.getElementById("year");
	  var currentYear = new Date().getFullYear(); // 현재 연도
	  var minYear = currentYear - 60; // 60세 이상을 위한 최소 연도
	  for (var year = minYear; year >= 1900; year--) { // minYear부터 1900년까지 차례로 option 추가
	    var option = document.createElement("option"); // 옵션 요소 생성
	    option.value = year; // value 속성 설정
	    option.text = year;  // 표시할 text 설정
	    yearSelect.appendChild(option); // selectBox에 옵션 추가
	  }
	  
	  // 월(1월 ~ 12월)
	  var monthSelect = document.getElementById("month");
	  for (var month = 1; month <= 12; month++) {
	    var option = document.createElement("option");
	    option.value = month;
	    option.text = month;
	    monthSelect.appendChild(option);
	  }

	  // 일(1일 ~ 31일)
	  var daySelect = document.getElementById("day");
	  for (var day = 1; day <= 31; day++) {
	    var option = document.createElement("option");
	    option.value = day;
	    option.text = day;
	    daySelect.appendChild(option);
	  }
	  
	  // POST 요청 : 회원가입
	  $('#signupForm').on('submit', function(event) {
	    event.preventDefault(); // 폼 기본 제출 차단
	
	    // 폼 데이터 JSON 객체로 생성
	    var formData = {
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
	
	    $.ajax({
	      url: '/member/signup',
	      type: 'POST',
	      contentType: 'application/json',
	      data: JSON.stringify(formData),
	      headers: {
	        'X-CSRF-TOKEN': '${_csrf.token}', // 스프링 시큐리티 사용
        	'Accept': 'application/json'  
	      },
	      success: function(response) {
	        alert('회원가입이 완료되었습니다.');
	        window.location.href = '/member/login';
	      },
	      error: function(xhr, status, error) {
	        alert('회원가입에 실패했습니다.');
	        console.error(error);
	      }
	    });
	  });
</script>
</body>
</html>