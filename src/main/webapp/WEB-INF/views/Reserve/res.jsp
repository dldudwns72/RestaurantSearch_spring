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
<!-- 예약목록 테이블과 찜한목록 테이블을 만들어야 할듯 -->
<title>예약 목록</title>


</head>
<body>
<form name="checktest" method="get" action="${pageContext.request.contextPath}/Reserve/res_list.do">
		<h1>
			<input type="hidden" id="getTitle" name="getTitle"
				value="${output.title}">
		</h1>
		<h1>${output.title}</h1>
		<hr />
		<h1>
			<input type="hidden" id="getchecktime" name="getchecktime"
				value="${checkdate}">
		</h1>
		<h2>예약 시간 : ${checkdate}</h2>
		<!-- Title과 Date값을 받아 rest_list.jsp로 넘긴다 -->
		<button type="submit" name="title" id="title" value='${title}'
			onclick="ReserveCheck()">예약하기</button>

		<!-- <button type = "submit" name = "checkdate" id = "check" onclick = "ReserveCheck()">예약확인</button>
 -->
		<input type="submit" name="checkdate" id="checkdate" value="예약확인"
			onclick="ReserveCheck()"> <input type="button" value="취소"
			onClick="window.close()">

		<script type="text/javascript">
var getTitle = "${output.title}";
var getcheckdate = "${checkdate}";
var getTitle = document.checktest.getTitle.value;
function ReserveCheck(){
	
	/* window.name="parentFrom"; */
	/* widnow.open("res_list.jsp","chkForm","width=400, height=300, left=100, top=50") */
	window.open("${pageContext.request.contextPath}/Reserve/res_list.do?title="+document.checktest.getTitle.value,"reservecheckform");
	window.close();
	
	
}

/* var ret = window.open("res_list.jsp","_blank")  */
</script>

	</form>
</body>



</html>