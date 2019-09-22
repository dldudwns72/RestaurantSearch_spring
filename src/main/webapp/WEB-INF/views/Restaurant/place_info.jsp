<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	/* function showPopup() { window.open("res.jsp", "a", "width=400, height=300, left=100, top=50"); } */
	function showPopup2() {
		window.open("cupon.do", "a", "width=400, height=300, left=100, top=50");
	}

	// 예약
	function reserve() {
		window.name = "parentForm";
		window.open("${pageContext.request.contextPath}/Reserve/res.do?restNo="
				+ document.reservation.restNo.value, "chkForm",
				"width=400, height=300, left=100, top=50");

	}
</script>


<!-- Bootstrap core CSS -->
<link
	href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link
	href="${pageContext.request.contextPath}/assets/css/shop-homepage.css"
	rel="stylesheet">
<body>
	<header>
		<%@ include file="header.jsp"%>
	</header>

	<div class="container">

		<div class="row">

			<div class="col-xl-9 mx-auto"></div>

			<div class="col-md-10 col-lg-8 col-xl-7 mx-auto">
				<form method="get"
					action="${pageContext.request.contextPath}/Restaurant/search.do">

					<div class="form-row">
						<div class="col-12 col-md-9 mb-2 mb-md-0">
							<input type="text" id="keyword" name="keyword"
								class="form-control form-control-lg"
								placeholder="음식점이나 메뉴를 검색해보세요.">
						</div>
						<div class="col-12 col-md-3">
							<button type="submit" class="btn btn-block btn-lg btn-primary">검색
							</button>
						</div>

					</div>
				</form>

				<br /> <br /> <br /> <br /> <br />

			</div>
		</div>
	</div>

	<div class="container">

		<div class="row">

			<div class="col-lg-3">

				<div class="list-group">
					<a href="${pageContext.request.contextPath}/Restaurant/cate_kor.do"
						class="list-group-item">한식</a> <a
						href="${pageContext.request.contextPath}/Restaurant/cate_chn.do"
						class="list-group-item">중식</a> <a
						href="${pageContext.request.contextPath}/Restaurant/cate_jpn.do"
						class="list-group-item">일식</a> <a
						href="${pageContext.request.contextPath}/Restaurant/cate_wtf.do"
						class="list-group-item">양식</a> <a
						href="${pageContext.request.contextPath}/Restaurant/cate_cafe.do"
						class="list-group-item">카페 및 디저트</a> <a
						href="${pageContext.request.contextPath}/Restaurant/cate_etc.do"
						class="list-group-item">기타</a>
				</div>

			</div>

			<div class="col-lg-4">
				<input type = "hidden" name = "query" id = "query" value="${output.title}"/> 
				<img src="${thumbnail}" width="350" height="300">
			</div>

			<div class="col-lg-4">
				<div class="card-body">
					<h3 align="center" class="card-title">${output.title }</h3>
					<hr />
					<ul>
						<li><h6 class="card-text">주소 : ${output.address}</h6></li>
						<hr />
						<li><h6 class="card-text">도로명주소 :${output.roadAddress}</h6></li>

						<hr />
						<c:choose>
							<c:when test="${output.telephone.equals(null)}">
								<h1>해당 음식점의 전화번호가 없습니다.</h1>
							</c:when>
							<c:otherwise>
								<li><h6 class="card-text">전화번호 :${output.telephone}</h6></li>
							</c:otherwise>
						</c:choose>

						<hr />
						<li><h6 class="card-text">카테고리 :${output.category}</h6></li>
						<hr />
						<li><h6 class="card-text">링크 : ${output.link}</h6></li>

						<hr />

						<div style='display: inline; float: left;'>
							<form action=cupon.jsp>
								<input type="button" value="쿠폰 발급" onclick="showPopup2();" />
							</form>
						</div>
						<div style='display: inline; float: left;'>

							<form name="reservation" id="reservation" method='get'>
								<%-- <input type="button" id ="restNo" value="<%=restNo %>" onclick="reserve()"> --%>
								<%-- <input type="button" id ="restNo" value="예약" onclick="reserve()">
 	<input type="hidden" id ="num" value ="<%=restNo%>"/> --%>
								<button type="submit" id="restNo" value="${output.restNo}"
									onclick="reserve()">예약</button>
								<!-- <input type="button" id="reserve" value="예약" onclick="reserve()" /> -->
							</form>
						</div>


					</ul>
				</div>
			</div>

		</div>
	</div>
	
	<footer>
		<%@ include file="footer.jsp"%>
	</footer>
</body>