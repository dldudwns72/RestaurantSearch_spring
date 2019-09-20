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

<title>Landing Page - Start Bootstrap Theme</title>

<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom fonts for this template -->
<link href="${pageContext.request.contextPath}/assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/vendor/simple-line-icons/css/simple-line-icons.css"
	rel="stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath}/assets/https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic"
	rel="stylesheet" type="text/css">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/assets/css/landing-page.min.css" rel="stylesheet">




<!-- 아래의 스크립트를 <HEAD></HEAD> 사이에 넣으세요. -->

<!-- <script language=javascript>
	function popup5() { //팝업창을 여러개 띄우는 함수를 지정합니다. 
		window
				.open(
						'notice.jsp',
						'newwin1',
						'toolbar=no, location=no, directories=no, status=no,menubar=no, scrollbars=no, resizable=no, copyhistory=yes, height=280, width=220, left=40, top=70') //첫번째 팝업창을 띄웁니다.
		window
				.open(
						'event.jsp',
						'newwin2',
						'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=yes, height=280, width=220, left=90, top=100') //두번째 팝업창을 띄웁니다. 
	
	} -->
</script>

<!--<HEAD></HEAD> 부분에 여기까지 넣습니다.-->

</head>

<!--<BODY> 태그내에 onload="" 혹은 onunload="" 부분을 넣으세요-->

<body onLoad="popup5()">
	<!--브라우저가 열릴 때 popup5()함수를 실행합니다.-->
<body>
<%-- <%
	if(loginInfo == null){
%> --%>
	<%-- <jsp:include page="header.jsp" flush="false" /> --%>
	<%-- <div>
	<jsp:include page="${pageContext.request.contextPath}/Restaurant/header.do" flush="true" />
	</div> --%>
	<header>
		<%@ include file = "header.jsp" %>
	</header>
	<header class="masthead text-white text-center">
		<div class="overlay"></div>
		<div class="container">
			<div class="row">
				<div class="col-xl-9 mx-auto">
					<h1 class="mb-5">원하시는 메뉴 혹은 가게이름을 적어주세요 .</h1>
				</div>
				<div class="col-md-10 col-lg-8 col-xl-7 mx-auto">
					<form method = "get" action ="${pageContext.request.contextPath}/Restaurant/search.do">
						<div class="form-row">
							<div class="col-12 col-md-9 mb-2 mb-md-0">
								<input type="text" id = "keyword" name = "keyword" 
								class="form-control form-control-lg" placeholder="검색창" value="${keyword}"> 
								<%-- <input type="hidden" name="query" id="query" value = "${query}"/> --%>
									<%-- <input type = "search" id = "query" name = "query" value =<%=query %>/> --%>
							</div>
							<div class="col-12 col-md-3">
								<button type="submit" class="btn btn-block btn-lg btn-primary">검색
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</header>
	
<%-- 	<%}else{ %>
	<jsp:include page="header2.jsp" flush="false" />
	
	<header class="masthead text-white text-center">
		<div class="overlay"></div>
		<div class="container">
			<div class="row">
				<div class="col-xl-9 mx-auto">
					<h1 class="mb-5">원하시는 메뉴 혹은 가게이름을 적어주세요 .</h1>
				</div>
				<div class="col-md-10 col-lg-8 col-xl-7 mx-auto">
					<form method = "get" action ="cate-Search.jsp">
						<div class="form-row">
							<div class="col-12 col-md-9 mb-2 mb-md-0">
								<input type="text" id = "keyword" name = "keyword" class="form-control form-control-lg" placeholder="검색창"> 
									<input type = "search" id = "query" name = "query" value =<%=query %>/>
							</div>
							<div class="col-12 col-md-3">
								<button type="submit" class="btn btn-block btn-lg btn-primary">검색
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</header>
	<%} %>
 --%>	<!-- Icons Grid -->
	<section class="features-icons bg-light text-center">
		<div class="container">
			<div class="row">
				<div class="col-lg-4">
					<div class="features-icons-item mx-auto mb-5 mb-lg-0 mb-lg-3">
						<div class="features-icons-icon d-flex">
							<a href="${pageContext.request.contextPath}/Restaurant/cate_kor.do">
							<img src="${pageContext.request.contextPath}/assets/img/food.jpg" width="300" height="160"></a>
						</div>
						<br><br />
						<h3>카테고리별</h3>
						<p class="lead mb-0">카테고리별 원하는 메뉴 탐색</p>
					</div>
				</div>
				<div class="col-lg-4">
					<div class="features-icons-item mx-auto mb-5 mb-lg-0 mb-lg-3">
						<div class="features-icons-icon d-flex">
							<a href="${pageContext.request.contextPath}/Random/random.do"><img src="${pageContext.request.contextPath}/assets/img/main_random.png" width="300" height="160"></a>
						</div>
							<br><br />
						<h3>랜덤메뉴</h3>
						<p class="lead mb-0">고르기 힘든 음식 메뉴 편하게 랜덤으로!</p>
					</div>
				</div>
					
				
				<div class="col-lg-4">
					<div class="features-icons-item mx-auto mb-0 mb-lg-3">
						<div class="features-icons-icon d-flex">
							<a href="rank.jsp"><img src="${pageContext.request.contextPath}/assets/img/rank.png" width="300" height="160"></a>
						</div>
						<br><br />
						<h3>맛집랭킹 TOP 10</h3>
						<p class="lead mb-0">현재 맛집 순위를 확인하세요</p>
					</div>
				</div>
			</div>
		</div>
	</section>
	<footer>
		<%@ include file = "footer.jsp" %>
	</footer>
	</body>

<%-- 	<jsp:include page="footer.jsp" flush="false" /> --%>
</html>




