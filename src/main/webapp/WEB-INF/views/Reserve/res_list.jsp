<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<script language="javascript" type="text/javascript">
	function checkAll(checkList, boolCheck) {
		varchkSize = 0;
		checkedCount = checkList.length;

		if (typeof (checkedCount) != "undefined") {
			for (var i = 0; i < chkSize; i++) {
				checkList[i].checked = boolCheck;
			}
		}
	}

	function checkDel() {
		var chkFirList = document.getElementsByName('checkNo');
		var arrFir = new Array();
		var cnt = 0;
		for (var idx = chkFirList.length - 1; 0 <= idx; idx--) {
			if (chkFirList[idx].checked) {
				arrFir[cnt] = chkFirList[idx].value;
				cnt++;
			}
		}
		if (arrFir.length != 0) {
			document.form1.submit();
		} else {
			alert('삭제할 목록을 선택하세요.');
			return;
		}
	}
	<title>게시판</title>
</script>

<meta charset="UTF-8">

<title>Insert title here</title>

<!-- Bootstrap core CSS -->

<link
	href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->

<link
	href="${pageContext.request.contextPath}/assets/css/shop-homepage.css"
	rel="stylesheet">
<body>
	<%-- <%if(loginInfo == null) {
		/* webHelper.redirect(null, "로그인 후 이용 가능합니다."); */
		out.println("로그인후 이용 가능");
	} else{
	%> --%>
	<%-- <jsp:include page="header.jsp" flush="false" /> --%>

	<!-- Page Content -->

	<div class="container">

		<div class="row">

			<div class="col-lg-3">

				<div class="list-group">

					<a href="mypage.do" class="list-group-item">내 정보 수정</a> <a
						href="preference.do" class="list-group-item">찜한 목록</a> <a
						href="res_list.do" class="list-group-item">예약 목록</a>

				</div>

			</div>

			<!-- /.col-lg-4 -->


			<div class="col-lg-9">

				<h1 align="center">예약 목록</h1>
				<form name="form2" method="get"
					action="${pageContext.request.contextPath}/Reserve/res_delete.do">
					<table width="100%" cellpadding="1" cellspacing="1" border="2">

						<tr
							style="background: url('img/table_mid.gif') repeat-x; text-align: center;">

							<td width="73">No</td>
							<td width="370">Value</td>
							<td width="170">Time</td>
							<td width="73">Name</td>

						</tr>

						<!-- 예약목록이라는 테이블 생성 -->
						<c:choose>
							<c:when test="$output == null || fn:length(output) == 0">
								<h1>조회결과 없다</h1>
							</c:when>
							<c:otherwise>
								<c:forEach var="item" items="${output}" varStatus="status">
									<c:set var="checkNo" value="${item.checkNo}" />
									<c:set var="title" value="${item.title}" />
									<c:set var="checkdate" value="${item.checkdate}" />

									<tr>

										<!-- CheckNo -->

										<td><input type="checkbox" id="checkNo" name="checkNo"
											value="${checkNo}">No.${checkNo}</td>
										<!-- Title -->
										<td>${title}</td>
										<!-- Date -->

										<td>${checkdate}</td>

										<%-- <td><%=loginInfo.getName() %></td> --%>
										<!-- UserId -->
									</tr>
									<tr height="1" bgcolor="#D2D2D2">
										<td colspan="6"></td>
									</tr>

									<tr height="1" bgcolor="#82B5DF">
										<td colspan="6" width="752"></td>
									</tr>

								</c:forEach>
							</c:otherwise>
						</c:choose>
					</table>


					<input type="submit" value="삭제">
				</form>

				<!-- 􏰄페이지번호 􏰄-->
				<c:forEach var="i" begin="${pageData.startPage}"
					end="${pageData.endPage}" varStatus="status">
					<c:url value="/Reserve/res_list.do" var="pageUrl">
						<c:param name="page" value="${i}" />
						<c:param name="keyword" value="${keyword}" />
					</c:url>
					<c:choose>
						<c:when test="${pageData.nowPage == i}">
							<strong>[${i}]</strong>
						</c:when>
						<c:otherwise>
							<a href="${pageUrl}">[${i}]</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<!-- 다음 페이지 그룹 링크  -->
				<c:choose>
					<c:when test="${pageData.nextPage > 0}">
						<c:url value="/Reserve/res_list.do" var="nextPageUrl">
							<c:param name="page" value="${pageData.nextPage}" />
							<c:param name="keyword" value="${keyword}" />
						</c:url>
						<a href="${nextPageUrl}">[다음]</a>
					</c:when>
					<c:otherwise> [다음]
					</c:otherwise>
				</c:choose>


			</div>


			<div class="col-lg-4"></div>
			<!-- /.col-lg-4 -->



		</div>
		<!-- /.row -->


	</div>
	<!-- /.col-lg-9 -->


	<!-- Custom styles for this template -->

	<link
		href="${pageContext.request.contextPath}/assets/css/shop-homepage.css"
		rel="stylesheet">
<body></body>
</html>