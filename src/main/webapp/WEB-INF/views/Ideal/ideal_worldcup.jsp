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
</head>
<body>


<header>
		<%@ include file="header.jsp"%>
	</header>
	
	
 <!-- ★★★★★★★★★★★★First game★★★★★★★★★★★★★ -->
<c:choose> 
 <c:when test="gamecount == 0">

<h1>Round ${gamecount+1}</h1>
  <form name="myform" method="post" action="${pageContext.request.contextPath}/Ideal/ideal_worldcup.do">
  
      <div>
         <img src=img/1.jpg>
         <img src=img/2.jpg>
      </div>
      
      
      <div>
         <label> <input type="radio" name="result1" value="1">1 </label>
         <label> <input type="radio" name="result1" value="2">2 </label>
      </div>
    
   <input type='hidden' name='result1' value='${result1}' />
   <input type='hidden' name='gamecount' value='${gamecount+1}' />
   <input type='hidden' name='g1' value='g1' />
   <input type='hidden' name='g2' value='g2' /> 

      
      <button type="submit">다음단계</button>
      
     
     <c:choose> 
     <c:when test="gamecount == 0">
      
	     <c:choose> 
	     <c:when test="result1 == 1">
            g1 = g1+1;
          </c:when>
          <c:when test="result1 == 2">
          	g2 = g2+1;
          </c:when>
          </c:choose>
          
   </c:when>
   </c:choose>

   </form>
   
   <!-- ★★★★★★★★★★★★2nd to 8th game★★★★★★★★★★★★★ -->
</c:when>   
 <c:when test="0 < gamecount && gamecount < 8">
            

   <h1>Round ${gamecount+1}</h1>

   <form name="myform" method="post" action="${pageContext.request.contextPath}/Ideal/ideal_worldcup.do">
      

   <!-- 이미지 띄우기 -->
      <div>
         <img src=img/'${gamecount*2+1}'.jpg>
         <img src=img/'${gamecount*2+2}'.jpg>
      </div>
      
   <!--  라디오버튼 만들기 -->   
      <div>
         <label> <input type="radio" name="result'${gamecount+1}'" value='${gamecount*2+1}'> '${gamecount*2+1}'  </label> <!-- 3, 5, 7, 9, 11, 13, 15 -->
         <label> <input type="radio" name="result'${gamecount+1}'" value='${gamecount*2+2}'> '${gamecount*2+2}'  </label> <!-- 4, 6, 8, 10, 12, 14, 16 -->
      </div>
   
   <!-- 값 보내기 -->
   
 
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
             g5 = g5+1;
              </c:when>
          <c:when test="result3 == 6">
             g6 = g6+1;
              </c:when>
              </c:choose>
      </c:when>
          
       <c:when test="gamecount == 3">
       <c:choose>   
           <c:when test="result4 == 7">
             g7 = g7+1;
           </c:when>
           <c:when test="result4 == 8">
             g8 = g8+1;
           </c:when>
       </c:choose>
       </c:when>
          
       <c:when test="gamecount == 4">
        <c:choose>  
           <c:when test="result5 == 9">
             g9 = g9+1;
           </c:when>
           <c:when test="result5 == 10">
             g10 = g10+1;
           </c:when>
         </c:choose>
        </c:when>
          
       <c:when test="gamecount == 5">
       <c:choose>
          <c:when test="result6 == 11">
             g11 = g11+1;
             </c:when>
          <c:when test="result6 == 12">
             g12 = g12+1;
             </c:when>
          </c:choose>
          </c:when>
          
       <c:when test="gamecount == 6">
         <c:choose>
          <c:when test="result7 == 13">
             g13 = g13+1;
             </c:when>
          <c:when test="result7 ==14">
             g14 = g14+1;
             </c:when>
          </c:choose>
          </c:when>
         
       <c:when test="gamecount == 7">
         <c:choose>
         <c:when test="result8 == 15">
             g15 = g15+1;
         </c:when>
         <c:when test="result8 == 16">
             g16 = g16+1;
         </c:when>
         </c:choose>
         </c:when>
         
      </c:choose>
      
   </form>
   
   

 <!-- ★★★★★★★★★★★★9th to 16th★★★★★★★★★★★★★ -->   
</c:when>
<c:when test="8 <= gamecount &&  gamecount < 15">


   
   <h1>Round ${gamecount+1}</h1>

   <form name="myform" method="post" action="${pageContext.request.contextPath}/Ideal/ideal_worldcup.do">

   <div>
       <img src=img/'${(gamecount)-7)*2-1}'.jpg>    
      <img src=img/'${(gamecount)-7)*2}'.jpg>
   </div>

         
   <!--  라디오버튼 만들기 -->

   <div>
       <label> <input type="radio" name="result'${gamecount+1}'" value="result'${(gamecount)-7)*2-1}'"> "result'${(gamecount)-7)*2-1}'" </label> 
       <label> <input type="radio" name="result'${gamecount+1}'" value="result'${(gamecount)-7)*2}'"> "result'${(gamecount)-7)*2}'" </label>
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
   <c:when test="gamecount == 9">
     	<c:choose>
     	   <c:when test="result10 == 5">
              g5 = g5+1;
           </c:when>
           <c:when test="result10 == 6">
              g6 = g6+1;
           </c:when>
           <c:when test="result10 == 7">
             g7 = g7+1;
           </c:when>
           <c:when test="result10 == 8">
             g8 = g8+1;
           </c:when>
         </c:choose>
    </c:when>
           
           
      <c:when test="gamecount == 10">
      <c:choose>
        <c:when test="result11 == 9">
              g9 = g9+1;
           </c:when>
       <c:when test="result11 == 10">
              g10 = g10+1;
           </c:when>
        <c:when test="result11 == 11">
             g11 = g11+1;
           </c:when>
        <c:when test="result11 == 12">
             g12 = g12+1;
       </c:when>    
       </c:choose>
       </c:when>
           
    <c:when test="gamecount == 11">
    <c:choose>
       		<c:when test="result12 == 13">
              g13 = g13+1;
              </c:when>
            <c:when test="result12 == 14">
              g14 = g14+1;
              </c:when>
            <c:when test="result12 == 15">
             g15 = g15+1;
             </c:when>
            <c:when test="result12 == 16">
             g16 = g16+1;
             </c:when>
    </c:choose>
    </c:when>
           
   <c:when test="gamecount == 12">
   	<c:choose>
         <c:when test="result13 == 1">
              g1 = g1+1;  
          </c:when>
          <c:when test="result13 == 2">
              g2 = g2+1;  
          </c:when>
          <c:when test="result13 == 3">
             g3 = g3+1;
          </c:when>
          <c:when test="result13 == 4">
             g4 = g4+1;
          </c:when>
          <c:when test="result13 == 5">
             g5 = g5+1;
          </c:when>
          <c:when test="result13 == 6">
             g6 = g6+1;
          </c:when>
          <c:when test="result13 == 7">
             g7 = g7+1;
          </c:when>
          <c:when test="result13 == 8">
             g8 = g8+1;
          </c:when>
 	</c:choose>   
  </c:when>
  
  <c:when test="gamecount == 13">
  		<c:choose>
           <c:when test="result14 == 9">
              g9 = g9+1;
           </c:when>
           <c:when test="result13 == 10">
              g10 = g10+1;
           </c:when>
           <c:when test="result13 == 11">
             g11 = g11+1;
           </c:when>
           <c:when test="result13 == 12">
             g12 = g12+1;
           </c:when>
           <c:when test="result13 == 13">
             g13 = g13+1;
           </c:when>
           <c:when test="result13 == 14">
             g14 = g14+1;
           </c:when>
           <c:when test="result13 == 15">
             g15 = g15+1;
           </c:when>
           <c:when test="result13 == 16">
             g16 = g16+1;
           </c:when>
  		</c:choose>
  </c:when>
  
  <c:when test="gamecount == 14">
       
       <c:choose>
	  	   <c:when test="result15 == 1">
              g1 = g1+1;
             </c:when>
           <c:when test="result15 == 2">
              g2 = g2+1;
             </c:when>
           <c:when test="result15 == 3">
             g3 = g3+1;
             </c:when>
           <c:when test="result15 == 4">
             g4 = g4+1;
             </c:when>
           <c:when test="result15 == 5">
             g5 = g5+1;
             </c:when>
           <c:when test="result15 == 6">
             g6 = g6+1;
             </c:when>
           <c:when test="result15 == 7">
             g7 = g7+1;
             </c:when>
            <c:when test="result15 == 8">
             g8 = g8+1;
             </c:when>
           <c:when test="result15 == 9">
             g9 = g9+1;
             </c:when>
           <c:when test="result15 == 10">
             g10 = g10+1;
             </c:when>
           <c:when test="result15 == 11">
             g11 = g11+1;
             </c:when>
           <c:when test="result13 == 12">
             g12 = g12+1;
             </c:when>
           <c:when test="result13 == 13">
             g13 = g13+1;
             </c:when>
           <c:when test="result13 == 14">
             g14 = g14+1;
             </c:when>
           <c:when test="result13 == 15">
             g15 = g15+1;
             </c:when>
            <c:when test="result13 == 16">
             g16 = g16+1;
             </c:when>
       		 </c:choose>
     </c:when>
     </c:choose>
     </form>
</c:when>           
   

 <!-- ★★★★★★★★★★★★Finish★★★★★★★★★★★★★ -->

	<c:when test="gamecount==15">

      <form name="myform" method="post" action="${pageContext.request.contextPath}/Ideal/ideal_worldcup.do">
      <input type='hidden' name='gamecount' value='${gamecount}' />
      <input type='hidden' name='result15' value='${result15}' />
      
      String menu = webHelper.getString("menu");
     
     <c:choose> 
     <c:when test="result15 == 1">menu = "치킨";</c:when>
     <c:when test="result15 == 2">menu = "피자";</c:when>
     <c:when test="result15 == 3">menu = "국밥";</c:when>
     <c:when test="result15 == 4">menu = "떡볶이";</c:when>
     <c:when test="result15 == 5">menu = "삼겹살";</c:when>
     <c:when test="result15 == 6">menu = "회";</c:when>
     <c:when test="result15 == 7">menu = "초밥";</c:when>
     <c:when test="result15 == 8">menu = "마라탕";</c:when>
     <c:when test="result15 == 9">menu = "스테이크";</c:when>
     <c:when test="result15 == 10">menu = "버거";</c:when>
     <c:when test="result15 == 11">menu = "짜장면";</c:when>
     <c:when test="result15 == 12">menu = "생선구이";</c:when>
     <c:when test="result15 == 13">menu = "타코";</c:when>
     <c:when test="result15 == 14">menu = "곱창";</c:when>
     <c:when test="result15 == 15">menu = "만두";</c:when>
     <c:when test="result15 == 16">menu = "족발";</c:when> 
     </c:choose>
      
<!--       <button type="submit">다음단계</button>  -->     

	   <h1>최종 선택 결과 : '${menu }'</h1>
	   <img src=img/'${result15}'.jpg>
	   
   	</form>
	</c:when>
</c:choose>     
</body>
</html>