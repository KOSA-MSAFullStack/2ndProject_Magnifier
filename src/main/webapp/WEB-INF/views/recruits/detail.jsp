<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>채용공고 상세</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/recruit-detail.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <jsp:include page="../common/navbar.jsp" />

    <div class="detail-container">
        <h1 id="recruit-title" class="main-title"></h1>
        
        <div class="detail-table">
            <div class="table-section">
                <div class="section-title">모집 요강</div>
                <table>
                    <tbody>
                        <tr>
                            <th>직무 내용</th>
                            <td id="recruit-content" class="content-cell"></td>
                        </tr>
                        <tr>
                            <th>경력 조건</th>
                            <td id="recruit-careerCondition"></td>
                        </tr>
                        <tr>
                            <th>학력</th>
                            <td id="recruit-education"></td>
                        </tr>
                        <tr>
                            <th>고용 형태</th>
                            <td id="recruit-employmentType"></td>
                        </tr>
                        <tr>
                            <th>모집 인원</th>
                            <td id="recruit-headCount"></td>
                        </tr>
                        <tr>
                            <th>근무 지역</th>
                            <td id="recruit-workingArea"></td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <div class="table-section">
                <div class="section-title">근무 조건</div>
                <table>
                    <tbody>
                        <tr>
                            <th>임금 조건</th>
                            <td id="recruit-salaryCondition"></td>
                        </tr>
                        <tr>
                            <th>근무 시간</th>
                            <td id="recruit-workingHours"></td>
                        </tr>
                        <tr>
                            <th>근무 형태</th>
                            <td id="recruit-workingType"></td>
                        </tr>
                        <tr>
                            <th>사회 보험</th>
                            <td id="recruit-insurance"></td>
                        </tr>
                        <tr>
                            <th>퇴직 급여</th>
                            <td id="recruit-retirementSalary"></td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <div class="table-section">
                <div class="section-title">접수 기간 및 방법</div>
                <table>
                    <tbody>
                        <tr>
                            <th>접수마감일</th>
                            <td id="recruit-deadLine"></td>
                        </tr>
                        <tr>
                            <th>전형단계</th>
                            <td id="recruit-step"></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            
            <div class="table-section">
                <div class="section-title">기업 정보</div>
                <table>
                    <tbody>
                        <tr>
                            <th>기업명</th>
                            <td id="enterprise-name">[기업 정보 연동 필요]</td>
                        </tr>
                        <tr>
                            <th>기업 주소</th>
                            <td id="enterprise-address">[기업 정보 연동 필요]</td>
                        </tr>
                        <tr>
                            <th>담당자 연락처</th>
                            <td id="recruit-contact"></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="button-container">
            <security:authorize access="hasRole('ROLE_MEMBER')">
                <button class="action-button">지원하기</button>
            </security:authorize>
            <security:authorize access="hasRole('ROLE_ENTERPRISE')">
                 <c:url var="modifyUrl" value="/recruits/modify">
                    <c:param name="recruitId" value="${pageContext.request.request.getAttribute("recruitId")}" />
                </c:url>
                <a href="${modifyUrl}" class="action-button">수정하기</a>
            </security:authorize>
        </div>
    </div>

<script>
$(document).ready(function() {
    const path = window.location.pathname;
    const pathParts = path.split('/');
    const recruitId = pathParts[pathParts.length - 1];

    // 수정하기 버튼의 URL에 recruitId를 동적으로 설정
    const modifyButton = $("a.action-button");
    if (modifyButton.length) {
        const currentHref = modifyButton.attr('href');
        // URL에 recruitId가 이미 포함되어 있지 않은 경우에만 추가 (중복 방지)
        if (currentHref.indexOf(recruitId) === -1) {
             modifyButton.attr('href', currentHref + "/" + recruitId);
        }
    }

    if (recruitId && !isNaN(recruitId)) {
        $.ajax({
            url: "${pageContext.request.contextPath}/recruits/api/detail/" + recruitId,
            type: "GET",
            dataType: "json",
            success: function(data) {
                if(data && data.recruit && data.enterprise) {
                    const recruit = data.recruit;
                    const enterprise = data.enterprise;

                    // 공고 정보 채우기
                    $('.main-title').text(recruit.title);
                    $('#recruit-content').html(recruit.content.replace(/\n/g, '<br>'));
                    $('#recruit-careerCondition').text(recruit.careerCondition);
                    $('#recruit-education').text(recruit.education);
                    $('#recruit-employmentType').text(recruit.employmentType);
                    $('#recruit-headCount').text(recruit.headCount);
                    $('#recruit-workingArea').text(recruit.workingArea);
                    $('#recruit-salaryCondition').text(recruit.salaryCondition);
                    $('#recruit-workingHours').text(recruit.workingHours);
                    $('#recruit-workingType').text(recruit.workingType);
                    $('#recruit-insurance').text(recruit.insurance);
                    $('#recruit-retirementSalary').text(recruit.retirementSalary);
                    $('#recruit-deadLine').text(recruit.deadLine);
                    $('#recruit-step').text(recruit.step);
                    $('#recruit-contact').text(recruit.contact);

                    // 기업 정보 채우기
                    $('#enterprise-name').text(enterprise.name);
                    $('#enterprise-address').text(enterprise.address + " " + enterprise.addressDetail);
                }
            },
            error: function(xhr, status, error) {
                console.error("공고 상세 정보를 불러오는 중 오류 발생: ", error);
                alert("공고 정보를 불러오는 데 실패했습니다.");
            }
        });
    }
});
</script>

</body>
</html>