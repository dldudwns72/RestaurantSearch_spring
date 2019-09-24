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

<style>
table {
	width: 100%;
}

table, th, td {
	border: 1px solid #bcbcbc;
}
</style>
</head>
<body>


 <%--  <header>
	<%@ include file="header.jsp"%>
  </header>  --%>

	<table border="1">

		<caption>
			<h1>랜덤 메뉴</h1>
		</caption>
		<thead>
			<tr>
				<th>한식</th>
				<th>일식</th>
				<th>중식</th>
				<th>양식</th>
				<th>카페 및 디저트</th>
				<th>기타</th>
			</tr>
		</thead>

		<tbody>


			<tr>
				<th><a href="random_menu_Kor.do?keyword_kor=${keyword_kor}"><img
						src="${pageContext.request.contextPath}/assets/img/kor.jpg" width="250" height="250"></a></th>
				<th><a href="random_menu_Jpn.do?keyword_jpn=${keyword_jpn}"><img
						src="${pageContext.request.contextPath}/assets/img/japan.jpg" width="250" height="250"></a></th>
				<th><a href="random_menu_Chn.do?keyword_chn=${keyword_chn}"><img
						src="${pageContext.request.contextPath}/assets/img/china.jpg" width="250" height="250"></a></th>
				<th><a href="random_menu_Wtf.do?keyword_wtf=${keyword_wtf}"><img
						src="${pageContext.request.contextPath}/assets/img/pasta.jpg" width="250" height="250"></a></th>
				<th><a href="random_menu_Cafe.do?keyword_cafe=${keyword_cafe}"><img
						src="${pageContext.request.contextPath}/assets/img/dessert.jpg" width="250" height="250"></a></th>
				<th><a href="random_menu_Etc.do?keyword_etc=${keyword_etc}"><img
						src="${pageContext.request.contextPath}/assets/img/ve.jpg" width="250" height="250"></a></th>
			</tr>
			
			
		</tbody>
		</table>
		
			<% 
 
        %>
        
      

			<!-- <tr>

				<td rowspan="2"></td>
			</tr> -->


		
	<%-- 	
	 <footer>
		<%@ include file="footer.jsp"%>
	</footer>  --%>
	
</body>
</html>