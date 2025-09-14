<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %> <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>공고 관리</title>
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/resources/css/common.css"
    />
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/resources/css/recruit.css"
    />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  </head>
  <body>
    <jsp:include page="../common/navbar.jsp" />

    <div class="main">
      <div class="recruitList">
        <!-- 기업별 공고 목록이 동적으로 여기에 추가됩니다. -->
      </div>
      <div class="button-container">
        <button class="add-button" onclick="location.href='${pageContext.request.contextPath}/recruits/register'">
          공고 추가
        </button>
        <button class="delete-button">공고 삭제</button>
      </div>
    </div>

    <script>
      $(document).ready(function () {
        $.ajax({
          url: "${pageContext.request.contextPath}/recruits/api/listbyid",
          type: "GET",
          dataType: "json",
          headers: {
            "X-CSRF-TOKEN": "${_csrf.token}", // GET 요청이지만 보안 컨텍스트 유지를 위해 포함
          },
          success: function (data) {
            const recruitListContainer = $(".recruitList");
            if (!data || data.length === 0) {
              recruitListContainer.html("<p>등록된 채용 공고가 없습니다.</p>");
              return;
            }

            data.forEach(function (recruit) {
              const detailUrl = '${pageContext.request.contextPath}/recruits/detail/' + recruit.recruitId;
              const recruitHtml =
                '<div class="recruit">' +
                    '<div class="recruit-info">' +
                        '<input type="checkbox" class="recruit-checkbox" name="selectedRecruits" value="' + recruit.recruitId + '">' +
                        '<span class="title">' + recruit.title + '</span>' +
                    '</div>' +
                    '<span class="details">' + recruit.workingArea + '</span>' +
                    '<div class="apply">' +
                        '<a href="' + detailUrl + '" class="applyButton">공고 상세</a>' +
                        '<span class="deadline">마감일 : ' + recruit.deadLine + '</span>' +
                    '</div>' +
                '</div>';
              recruitListContainer.append(recruitHtml);
            });
          },
          error: function (xhr, status, error) {
            console.error("공고 목록을 불러오는 중 오류 발생: ", error);
            if (xhr.status === 403) {
              $(".recruitList").html(
                "<p>공고를 조회할 권한이 없습니다. 로그인 상태를 확인해주세요.</p>"
              );
            } else {
              $(".recruitList").html(
                "<p>공고를 불러오는 중 오류가 발생했습니다.</p>"
              );
            }
          },
        });

        // 공고 삭제 버튼 클릭 이벤트
        $(".delete-button").on("click", function () {
          const selectedRecruitIds = [];
          // 체크된 체크박스의 recruitId 값 수집
          $(".recruit-checkbox:checked").each(function () {
            selectedRecruitIds.push($(this).val());
          });

          if (selectedRecruitIds.length === 0) {
            alert("삭제할 공고를 선택해주세요.");
            return;
          }

          if (confirm("선택된 공고를 정말 삭제하시겠습니까?")) {
            $.ajax({
              url: "${pageContext.request.contextPath}/recruits/api/delete/bulk",
              type: "DELETE",
              contentType: "application/json",
              data: JSON.stringify(selectedRecruitIds), // ID 리스트를 JSON 배열로 전송
              headers: {
                "X-CSRF-TOKEN": "${_csrf.token}",
              },
              success: function (response) {
                alert("선택된 공고가 성공적으로 삭제되었습니다.");
                location.reload(); // 페이지 새로고침하여 목록 업데이트
              },
              error: function (xhr, status, error) {
                console.error("공고 삭제 중 오류 발생:", error);
                alert("공고 삭제에 실패했습니다: " + xhr.responseText);
              },
            });
          }
        });
      });
    </script>
  </body>
</html>
