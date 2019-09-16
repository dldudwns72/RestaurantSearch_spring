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
				<hr />
				<h6 align="center">검색결과 총 ${totalCount} 건</h6>
				<hr />

			</div>
		</div>
	</div>

	<div class="container">

		<div class="row">

			<div class="col-lg-3">

				<div class="list-group">
					<a href="cate_kor.do" class="list-group-item">한식</a> <a
						href="cate_chn.do" class="list-group-item">중식</a> <a
						href="cate_jpn.do" class="list-group-item">일식</a> <a
						href="cate_wtf.do" class="list-group-item">양식</a> <a
						href="cate_cafe.do" class="list-group-item">카페</a> <a
						href="cate_etc.do" class="list-group-item">기타</a>

				</div>

			</div>

			<!-- /.col-lg-3 -->

			<div class="col-lg-8">
				<h1>검색 결과</h1>
				<div class="row">

					<c:choose>
						<c:when test="$output == null || fn:length(output) == 0">
							<h1>조회결과 없다</h1>
						</c:when>
						<c:otherwise>
							<c:forEach var="item" items="${output}" varStatus="status">
								<c:set var="restNo" value="${item.restNo}" />
								<c:set var="title" value="${item.title}" />
								<c:set var="address" value="${item.address}" />
								<c:set var="link" value="${item.link}" />
								<c:set var="telephone" value="${item.telephone}" />
								<c:set var="category" value="${item.category}" />
								<div class="col-lg-4 col-md-6 mb-4">
									<!-- <form method = "get" action = "cate-Search.jsp"> -->
									<div class="card h-100">
										<a
											href="${pageContext.request.contextPath}/Restaurant/place_info.do">
											<img src="#" width="222" height="128">
										</a>
										<div class="card-body">
											<h4 class="card-title">

												<!-- %title -->
												<a
													href="${pageContext.request.contextPath}/Restaurant/place_info.do?restNo=${restNo}">${title}</a>
											</h4>
											<!-- %telephone -->
											<h5>${telephone}</h5>
											<!-- %address -->
											<p class="card-text">${address}</p>

										</div>

										<div class="card-footer">
											<small class="text-muted">&#9733; &#9733; &#9733;
												&#9733; &#9734;</small>
										</div>

									</div>
									<!-- </form> -->
								</div>

							</c:forEach>
						</c:otherwise>
					</c:choose>

				</div>
				<!-- /.row -->

				<!-- 􏰄페이지번호 􏰄-->
				<c:forEach var="i" begin="${pageData.startPage}"
					end="${pageData.endPage}" varStatus="status">
					<c:url value="/Restaurant/cate_wtf.do" var="pageUrl">
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
						<c:url value="/Restaurant/cate_wtf.do" var="nextPageUrl">
							<c:param name="page" value="${pageData.nextPage}" />
							<c:param name="keyword" value="${keyword}" />
						</c:url>
						<a href="${nextPageUrl}">[다음]</a>
					</c:when>
					<c:otherwise> [다음]
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
	
	<footer>
		<%@ include file="footer.jsp"%>
	</footer>
</body>
<script
	src="${pageContext.request.contextPath}/assets/vendor/jquery/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</html>