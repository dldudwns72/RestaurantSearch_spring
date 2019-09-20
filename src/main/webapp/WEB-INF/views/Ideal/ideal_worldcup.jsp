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


<link
	href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<link
	href="${pageContext.request.contextPath}/assets/css/shop-homepage.css"
	rel="stylesheet">
	
</head>
<body>


<header>
		<%@ include file="header.jsp"%>
	</header>
	
	<!-- if문 돌리기 -->
	<!-- gamecount의 초기값을 main에서 받아와야하는데...안오는것같음? -->
	
<c:choose> 

 <c:when test=" ${gamecount} == 0">

<h1>Round ${gamecount+1}</h1>
  <form name="myform" method="post" action="${pageContext.request.contextPath}/Ideal/ideal_worldcup.do">
  
      <div>
	      <img src="${pageContext.request.contextPath}/assets/img/1.jpg">
	      <img src="${pageContext.request.contextPath}/assets/img/2.jpg">
	      
      </div>
      
      
      <div>
         <label> <input type="radio" name="result1" value="1">1 </label>
         <label> <input type="radio" name="result1" value="2">2 </label>
      </div>
    
      <input type='hidden' name='result1' value='${result1}' />
      <input type='hidden' name='gamecount' value='${gamecount+1}' />
      <input type='hidden' name='g1' value='${g1}' />
      <input type='hidden' name='g2' value='${g2}' />
      
      <button type="submit">다음단계</button>
      
     
     <c:choose> 
     <c:when test="gamecount == 0">
      
	     <c:choose> 
 	      <c:when test="result1 == 1">
           <!-- g1 = g1+1; -->
	      <c:set var="g1" value='${g1+1}'/>
	      </c:when>
          
          <c:when test="result1 == 2">
          <!-- 	g2 = g2+1; -->
          <c:set var="g2" value='${g2+1}'/>
	      </c:when>
          </c:choose>
          
   </c:when>
   </c:choose>

   </form>
   
</c:when>   
 <c:when test="0 < ${gamecount} && ${gamecount} < 8">
            

   <h1>Round ${gamecount+1}</h1>

   <form name="myform" method="post" action="${pageContext.request.contextPath}/Ideal/ideal_worldcup.do">
      

      <div>
      	<img src="${pageContext.request.contextPath}/assets/img/${gamecount*2+1}.jpg">
      	<img src="${pageContext.request.contextPath}/assets/img/${gamecount*2+2}.jpg">
      </div>
      
      <div>
         <label> <input type="radio" name="result'${gamecount+1}'" value='${gamecount*2+1}'> '${gamecount*2+1}'  </label> <!-- 3, 5, 7, 9, 11, 13, 15 -->
         <label> <input type="radio" name="result'${gamecount+1}'" value='${gamecount*2+2}'> '${gamecount*2+2}'  </label> <!-- 4, 6, 8, 10, 12, 14, 16 -->
      </div>
   
 
      <input type='hidden' name='gamecount' value='${gamecount+1}' />
      
      <input type='hidden' name='result1' value='${result1}' />
      <input type='hidden' name='result2' value='${result2}' />
      <input type='hidden' name='result3' value='${result3}' />
      <input type='hidden' name='result4' value='${result4}' />
      <input type='hidden' name='result5' value='${result5}' />
      <input type='hidden' name='result6' value='${result6}' />
      <input type='hidden' name='result7' value='${result7}' />
      <input type='hidden' name='result8' value='${result8}' />
      
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
   
      <button type="submit">다음단계</button>
      
      <c:choose>
      <c:when test="gamecount == 2">
         <c:choose>
           <c:when test="result3 == 5">
          	<c:set var="g5" value='${g5+1}'/>
           </c:when>
          
           <c:when test="result3 == 6">
            <c:set var="g6" value='${g6+1}'/>
            </c:when>
          </c:choose>
      </c:when>
          
       <c:when test="gamecount == 3">
       <c:choose>   
           <c:when test="result4 == 7">
             <c:set var="g7" value='${g7+1}'/>
           </c:when>
           <c:when test="result4 == 8">
             <c:set var="g8" value='${g8+1}'/>
           </c:when>
       </c:choose>
       </c:when>
          
       <c:when test="gamecount == 4">
        <c:choose>  
           <c:when test="result5 == 9">
             <c:set var="g9" value='${g9+1}'/>
           </c:when>
           <c:when test="result5 == 10">
             <c:set var="g10" value='${g10+1}'/>
           </c:when>
         </c:choose>
        </c:when>
          
       <c:when test="gamecount == 5">
       <c:choose>
          <c:when test="result6 == 11">
             <c:set var="g11" value='${g11+1}'/>
             </c:when>
          <c:when test="result6 == 12">
             <c:set var="g12" value='${g12+1}'/>
             </c:when>
          </c:choose>
          </c:when>
          
       <c:when test="gamecount == 6">
         <c:choose>
          <c:when test="result7 == 13">
            <c:set var="g13" value='${g13+1}'/>
             </c:when>
          <c:when test="result7 ==14">
            <c:set var="g14" value='${g14+1}'/>
             </c:when>
          </c:choose>
          </c:when>
         
       <c:when test="gamecount == 7">
         <c:choose>
         <c:when test="result8 == 15">
             <c:set var="g15" value='${g15+1}'/>
         </c:when>
         <c:when test="result8 == 16">
            <c:set var="g16" value='${g16+1}'/>
         </c:when>
         </c:choose>
         </c:when>
         
      </c:choose>
      
   </form>
   
   


</c:when>
<c:when test="8 <= ${gamecount} &&  ${gamecount} < 15">


   
   <h1>Round ${gamecount+1}</h1>

   <form name="myform" method="post" action="${pageContext.request.contextPath}/Ideal/ideal_worldcup.do">

   <div>
	   <img src="${pageContext.request.contextPath}/assets/img/${((gamecount)-7)*2-1}.jpg">
	   <img src="${pageContext.request.contextPath}/assets/img/${((gamecount)-7)*2}.jpg">
   </div>

        

   <div>
       <label> <input type="radio" name="result${gamecount+1}" value="result${((gamecount)-7)*2-1}"> </label> 
       <label> <input type="radio" name="result${gamecount+1}" value="result${((gamecount)-7)*2}"> </label>
    </div>    

      <input type='hidden' name='gamecount' value='${gamecount+1}' />
      
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

   <button type="submit">다음단계</button>
   
 <c:choose>
 
    <c:when test="gamecount == 8">
     	<c:choose>
	  	   <c:when test="result9 == 1">
              <c:set var="g1" value='${g1+1}'/>
             </c:when>
           <c:when test="result9 == 2">
              <c:set var="g2" value='${g2+1}'/>
             </c:when>
           <c:when test="result9 == 3">
             <c:set var="g3" value='${g3+1}'/>
             </c:when>
           <c:when test="result9 == 4">
             <c:set var="g4" value='${g4+1}'/>
             </c:when>
         </c:choose>
    </c:when>
           
 
 
   <c:when test="gamecount == 9">
     	<c:choose>
           <c:when test="result10 == 5">
             <c:set var="g5" value='${g5+1}'/>
             </c:when>
           <c:when test="result10 == 6">
             <c:set var="g6" value='${g6+1}'/>
             </c:when>
           <c:when test="result10 == 7">
             <c:set var="g7" value='${g7+1}'/>
             </c:when>
           <c:when test="result10 == 8">
             <c:set var="g8" value='${g8+1}'/>
             </c:when>
         </c:choose>
    </c:when>
           
           
      <c:when test="gamecount == 10">
      <c:choose>
           <c:when test="result11 == 9">
             <c:set var="g9" value='${g9+1}'/>
             </c:when>
           <c:when test="result11 == 10">
             <c:set var="g10" value='${g10+1}'/>
             </c:when>
           <c:when test="result11 == 11">
             <c:set var="g11" value='${g11+1}'/>
             </c:when>
           <c:when test="result11 == 12">
             <c:set var="g12" value='${g12+1}'/>
             </c:when>
       </c:choose>
       </c:when>
           
    <c:when test="gamecount == 11">
    <c:choose>
           <c:when test="result11 == 13">
             <c:set var="g13" value='${g13+1}'/>
             </c:when>
           <c:when test="result11 == 14">
             <c:set var="g14" value='${g14+1}'/>
             </c:when>
           <c:when test="result11 == 15">
             <c:set var="g15" value='${g15+1}'/>
             </c:when>
           <c:when test="result11 == 16">
            <c:set var="g16" value='${g16+1}'/>
             </c:when>
    </c:choose>
    </c:when>
           
   <c:when test="gamecount == 12">
   	<c:choose>
	  	   <c:when test="result13 == 1">
              <c:set var="g1" value='${g1+1}'/>
             </c:when>
           <c:when test="result13 == 2">
              <c:set var="g2" value='${g2+1}'/>
             </c:when>
           <c:when test="result13 == 3">
             <c:set var="g3" value='${g3+1}'/>
             </c:when>
           <c:when test="result13 == 4">
             <c:set var="g4" value='${g4+1}'/>
             </c:when>
           <c:when test="result13 == 5">
             <c:set var="g5" value='${g5+1}'/>
             </c:when>
           <c:when test="result13 == 6">
             <c:set var="g6" value='${g6+1}'/>
             </c:when>
           <c:when test="result13 == 7">
             <c:set var="g7" value='${g7+1}'/>
             </c:when>
            <c:when test="result13 == 8">
             <c:set var="g8" value='${g8+1}'/>
             </c:when>
 	</c:choose>   
  </c:when>
  
  <c:when test="gamecount == 13">
  		<c:choose>
           <c:when test="result14 == 9">
             <c:set var="g9" value='${g9+1}'/>
             </c:when>
           <c:when test="result14 == 10">
             <c:set var="g10" value='${g10+1}'/>
             </c:when>
           <c:when test="result14 == 11">
             <c:set var="g11" value='${g11+1}'/>
             </c:when>
           <c:when test="result14 == 12">
             <c:set var="g12" value='${g12+1}'/>
             </c:when>
           <c:when test="result14 == 13">
             <c:set var="g13" value='${g13+1}'/>
             </c:when>
           <c:when test="result14 == 14">
             <c:set var="g14" value='${g14+1}'/>
             </c:when>
           <c:when test="result14 == 15">
             <c:set var="g15" value='${g15+1}'/>
             </c:when>
            <c:when test="result14 == 16">
            <c:set var="g16" value='${g16+1}'/>
             </c:when>
  		</c:choose>
  </c:when>
  
  <c:when test="gamecount == 14">
       
       <c:choose>
	  	   <c:when test="result15 == 1">
              <c:set var="g1" value='${g1+1}'/>
             </c:when>
           <c:when test="result15 == 2">
              <c:set var="g2" value='${g2+1}'/>
             </c:when>
           <c:when test="result15 == 3">
             <c:set var="g3" value='${g3+1}'/>
             </c:when>
           <c:when test="result15 == 4">
             <c:set var="g4" value='${g4+1}'/>
             </c:when>
           <c:when test="result15 == 5">
             <c:set var="g5" value='${g5+1}'/>
             </c:when>
           <c:when test="result15 == 6">
             <c:set var="g6" value='${g6+1}'/>
             </c:when>
           <c:when test="result15 == 7">
             <c:set var="g7" value='${g7+1}'/>
             </c:when>
            <c:when test="result15 == 8">
             <c:set var="g8" value='${g8+1}'/>
             </c:when>
           <c:when test="result15 == 9">
             <c:set var="g9" value='${g9+1}'/>
             </c:when>
           <c:when test="result15 == 10">
             <c:set var="g10" value='${g10+1}'/>
             </c:when>
           <c:when test="result15 == 11">
             <c:set var="g11" value='${g11+1}'/>
             </c:when>
           <c:when test="result13 == 12">
             <c:set var="g12" value='${g12+1}'/>
             </c:when>
           <c:when test="result13 == 13">
             <c:set var="g13" value='${g13+1}'/>
             </c:when>
           <c:when test="result13 == 14">
             <c:set var="g14" value='${g14+1}'/>
             </c:when>
           <c:when test="result13 == 15">
             <c:set var="g15" value='${g15+1}'/>
             </c:when>
            <c:when test="result13 == 16">
            <c:set var="g16" value='${g16+1}'/>
             </c:when>
       		 </c:choose>
     </c:when>
     </c:choose>
     </form>
</c:when>           
   


	<c:when test="${gamecount}==15">

      <form name="myform" method="post" action="${pageContext.request.contextPath}/Ideal/ideal_worldcup.do">
      <input type='hidden' name='gamecount' value='${gamecount}' />
      <input type='hidden' name='result15' value='${result15}' />
      
     <c:set />
     
     <c:choose> 
     <c:when test="result15 == 1"><c:set var="menu" value="치킨"/></c:when>
     <c:when test="result15 == 2"><c:set var="menu" value="피자"/></c:when>
     <c:when test="result15 == 3"><c:set var="menu" value="국밥"/></c:when>
     <c:when test="result15 == 4"><c:set var="menu" value="떡볶이"/></c:when>
     <c:when test="result15 == 5"><c:set var="menu" value="삼겹살"/></c:when>
     <c:when test="result15 == 6"><c:set var="menu" value="회"/></c:when>
     <c:when test="result15 == 7"><c:set var="menu" value="초밥"/></c:when>
     <c:when test="result15 == 8"><c:set var="menu" value="마라탕"/></c:when>
     <c:when test="result15 == 9"><c:set var="menu" value="스테이크"/></c:when>
     <c:when test="result15 == 10"><c:set var="menu" value="버거"/></c:when>
     <c:when test="result15 == 11"><c:set var="menu" value="짜장면"/></c:when>
     <c:when test="result15 == 12"><c:set var="menu" value="생선구이"/></c:when>
     <c:when test="result15 == 13"><c:set var="menu" value="타코"/></c:when>
     <c:when test="result15 == 14"><c:set var="menu" value="곱창"/></c:when>
     <c:when test="result15 == 15"><c:set var="menu" value="만두"/></c:when>
     <c:when test="result15 == 16"><c:set var="menu" value="족발"/></c:when> 
     </c:choose>
      

	   <h1>최종 선택 결과 : ${menu}</h1>
	   <img src="${pageContext.request.contextPath}/assets/img/${result15}.jpg">
	   
   	</form>
	</c:when>
</c:choose>     
</body>
</html>