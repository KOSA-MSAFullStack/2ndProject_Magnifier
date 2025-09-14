<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <title>기업회원 | 내 정보</title>
    <link rel="stylesheet" href="/resources/css/signup.css" />
    <link rel="stylesheet" href="/resources/css/common.css" />
    <!-- jquery 라이브러리 추가 -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- 다음 우편번호 서비스 추가 -->
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>
    <div class="container">
        <div class="background"></div>
        <!-- 공통 네비게이션 바 포함 -->
       	<%@ include file="/WEB-INF/views/common/navbar.jsp" %>
        <div class="page-title">기업 정보</div>
        
        <!-- 회원가입 입력 폼 -->
        <form id="modifyForm" class="signup-form">
            <div class="form-group name-group">
	            <!-- 기업명 입력란 -->
                <input type="text" id="name" name="name" placeholder="기업명" class="input-box large" readonly/>
            </div>
            
            <!-- 사업자 등록 번호 입력 및 중복확인 버튼 -->
            <div class="form-group id-group">
                <input type="text" id="registerNumber" name="" placeholder="사업자 등록 번호(숫자만)" class="input-box large" readonly/>
            </div>
            <div id="idCheckMsg"></div>

            <!-- 우편번호 입력 및 우편번호 찾기 버튼 -->
            <div class="form-group postal-group">
                <input type="text" id="postNumber" name="postNumber" placeholder="우편번호" class="input-box medium" required/>
                <button type="button" id="findAddressBtn" class="btn-postal-search">우편번호 찾기</button>
            </div>
            
            <div class="form-group">
                <input type="text" id="address" name="address" placeholder="주소" class="input-box" required/>
            </div>

            <div class="form-group address-group">
                <input type="text" id="addressDetail" name="addressDetail" placeholder="상세 주소" class="input-box" required/>
                <input type="text" id="reference" name="reference" placeholder="참고 항목" class="input-box"/>
            </div>
            
           	<!-- 수정하기 제출 버튼 -->
            <button type="submit" class="btn-submit">수정하기</button>
            
            <!-- CSRF 토큰 히든 필드 (스프링 시큐리티용) -->
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </div>
    <script >
	  // 페이지 로드 시에도 회원 정보 로드 실행
	    $(document).ready(function() {
	      reloadMemberInfo(); // 회원 정보 조회
	    });
	  
	  // 회원 정보 다시 불러와서 폼에 채우는 메서드
	  function reloadMemberInfo() {
	    $.ajax({
	      url: '/enterprises/api/mypage',
	      type: 'GET',
	      success: function(response) {
	        $('#name').val(response.name);
	        $('#registerNumber').val(response.registerNumber);
	        $('#year').val(response.year);
	        $('#month').val(response.month);
	        $('#day').val(response.day);
	        $('#postNumber').val(response.postNumber);
	        $('#address').val(response.address);
	        $('#addressDetail').val(response.addressDetail);
	        $('#reference').val(response.reference);
	      },
	      error: function() {
	        alert('회원 정보를 불러오는 데 실패했습니다.');
	      }
	    });
	  }
	  
	  /*
	  	우편번호 찾기
	  */
	  $('#findAddressBtn').on('click', function(event) {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("reference").value = extraAddr;
                
                } else {
                    document.getElementById("reference").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postNumber').value = data.zonecode;
                document.getElementById("address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("addressDetail").focus();
            }
        }).open();
      });
	  	  
	  /*
	    PUT 요청 : 정보 수정 폼 제출 시 AJAX 처리
	  */
	  $('#modifyForm').on('submit', function(event) {
	    event.preventDefault(); // 폼 기본 제출 차단

	    // 폼 데이터 JSON 객체로 생성
	    const formData = {
	      "postNumber": $('#postNumber').val(),
	      "address": $('#address').val(),
	      "addressDetail": $('#addressDetail').val(),
	      "reference": $('#reference').val()
	    };

		// AJAX POST 요청
	    $.ajax({
	      url: '/enterprises/api/mypage',  // 회원정보 수정 처리 컨트롤러 URL
	      type: 'PUT',
	      contentType: 'application/json', // JSON 형식으로 전송
	      data: JSON.stringify(formData),  // JSON 문자열로 변환 후 전송
	      headers: {
	        'X-CSRF-TOKEN': '${_csrf.token}', // 스프링 시큐리티 사용
        	'Accept': 'application/json'  
	      },
	      success: function(response) {
	        alert('기업 정보 수정이 완료되었습니다.');
	        reloadMemberInfo(); // 회원 정보 조회
          },
	      error: function(xhr, status, error) {
	        alert('기업 정보 수정이 실패했습니다.');
	        console.error(error);
	      }
	    });
	  });
</script>
</body>
</html>