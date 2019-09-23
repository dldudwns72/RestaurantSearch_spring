<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
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

<script src="js/fun-btn.js"></script>	
<link href="${pageContext.request.contextPath}/assets/css/fun-btn.css" rel="stylesheet">
	
</head>
<body>

<header>
		<%@ include file="header.jsp"%>
	</header>


<form name="myform" method="post" action="${pageContext.request.contextPath}/Ideal/ideal_worldcup.do">
      <input type='hidden' name='gamecount' value='${gamecount}' />
      
      <input type='hidden' name='g1' value='${g1}' />
      <input type='hidden' name='g2' value='${g2}' />
      <input type='hidden' name='g3' value='${g3}' />
      <input type='hidden' name='g4' value='${g4}' />
      <input type='hidden' name='g5' value='${g5}' />
      <input type='hidden' name='g6' value='${g6}' />
      <input type='hidden' name='g7' value='${g7}' />
      <input type='hidden' name='g8' value='${g8}' />
      <input type='hidden' name='g9' value='${g9}' />
      <input type='hidden' name='g10' value='${g10}' />
      <input type='hidden' name='g11' value='${g11}' />
      <input type='hidden' name='g12' value='${g12}' />
      <input type='hidden' name='g13' value='${g13}' />
      <input type='hidden' name='g14' value='${g14}' />
      <input type='hidden' name='g15' value='${g15}' />      
      <input type='hidden' name='g16' value='${g16}' />

      <input type='hidden' name='result1' value='${result1}' />
      <input type='hidden' name='result2' value='${result2}' />
      <input type='hidden' name='result3' value='${result3}' />
      <input type='hidden' name='result4' value='${result4}' />
      <input type='hidden' name='result5' value='${result5}' />
      <input type='hidden' name='result6' value='${result6}' />
      <input type='hidden' name='result7' value='${result7}' />
      <input type='hidden' name='result8' value='${result8}' />
      <input type='hidden' name='result9' value='${result9}' />
      <input type='hidden' name='result10' value='${result10}' />
      <input type='hidden' name='result11' value='${result11}' />
      <input type='hidden' name='result12' value='${result12}' />
      <input type='hidden' name='result13' value='${result13}' />
      <input type='hidden' name='result14' value='${result14}' />
      <input type='hidden' name='result15' value='${result15}' />


      <div class="page">

	  <button class="fun-btn">Let's start!</button>
	
		</div>
      
   </form>
   
   <footer>
		<%@ include file="footer.jsp"%>
	</footer>
	

</body>
</html>