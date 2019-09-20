<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8" />
<title>Hello JSP</title>
</head>
<body>
	<h1>네이버 이미지 검색</h1>
     <form name="form1" method="get"
		action="${pageContext.request.contextPath}/Retrofit/naver_image.do">
		<label for="query">검색어: </label>
		<input type = "search" name ="query" id = "query" value = "${query}">	
		 <input type="submit" value="검색" />
	</form> 
	 <c:if test="${img_search !=null || fn:length(img_search.items) > 0}">
		<hr />
		<table border="1">
			<c:forEach var="item" items="${img_search.items}" varStatus="status">
				<c:if test="${status.index % 4 == 0}">
					<tr>
				</c:if>
				<td><a href="${item.title}">
				<img src="${item.thumbnail}" /></a>
				</td>
				<c:if test="${(status.index+1) % 4 == 0}">
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</c:if> 
</body>
</html>