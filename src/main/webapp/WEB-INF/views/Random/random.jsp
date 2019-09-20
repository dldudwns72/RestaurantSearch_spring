<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Business Frontpage - Start Bootstrap Template</title>

<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/assets/css/business-frontpage.css" rel="stylesheet">
	<%@ include file = "header.jsp" %>
</head>

<body>

	<!-- Page Content -->
	<div class="container">

		<%-- <jsp:include page="header.jsp" flush="false" /> --%>

		<div class="row">
			<div class="col-md-4 mb-5">
				<div class="card h-100">
					<a href="${pageContext.request.contextPath}/Ideal/ideal_main.do"><img class="card-img-top"
						src="${pageContext.request.contextPath}/assets/img/tournament.png" alt="" height=400>
						<div class="card-body">
							<h4 class="card-title" align="center">이상형 월드컵</h4>

						</div>
				</div>
			</div>

			<%-- <div class="col-md-4 mb-5">
				<div class="card h-100">
					<a href="random_menu.jsp?keyword2=<%=keyword2%>"><img class="card-img-top" src="img/cup.jpg" alt="" height=400></a>
					<div class="card-body">
						<input type ="hidden" name = "keyword2" value="<%=keyword2 %>">
						<h4 class="card-title" align="center">랜덤 메뉴 추천!</h4>
					</div>
					
					
				</div>
			</div> --%>

			<div class="col-md-4 mb-5">
				<div class="card h-100">
					<a href="${pageContext.request.contextPath}/Random/random_list.do"><img class="card-img-top"
						src="${pageContext.request.contextPath}/assets/img/random.png" alt="" height=400></a>
					<div class="card-body">
						<%-- <input type ="hidden" name = "keyword2" value="<%=keyword2 %>"> --%>
						<h4 class="card-title" align="center">랜덤 메뉴 추천</h4>
					</div>


				</div>
			</div>

			<div class="col-md-4 mb-5">
				<div class="card h-100">
					<a href="data.jsp"><img class="card-img-top" src="${pageContext.request.contextPath}/assets/img/data.png"
						alt="" height=400>
						<div class="card-body">
							<h4 class="card-title" align="center">데이터 기반 추천</h4>
						</div>
				</div>
			</div>

		</div>
		<!-- /.row -->

	</div>
	<!-- /.container -->



	<!-- Bootstrap core JavaScript -->
	<script src="${pageContext.request.contextPath}/assets/vendor/jquery/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<%-- <jsp:include page="footer.jsp" flush="false" /> --%>
</body>

</html>