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
	
	<style>
        #button2{
            border-top-left-radius: 5px;
            border-bottom-left-radius: 5px;
            margin-right:-4px;
            width:90px;
            height:40px;
        }
        #button2{
            border-top-right-radius: 5px;
            border-bottom-right-radius: 5px;    
            margin-left:-3px;
              width:90px;
            height:40px;
        }
        #btn_group button{
            border: 1px solid skyblue;
            background-color: rgba(0,0,0,0);
            color: skyblue;
            padding: 5px;
              width:90px;
            height:40px;
        }
        #btn_group button:hover{
            color:white;
            background-color: skyblue;
            width:90px;
            height:40px;
        }
</style>
	
</head>
<body>


<header>
		<%@ include file="header.jsp"%>
	</header>
	
	<!-- g1,g2 에 값넣기 -->

<c:choose> 

 <c:when test="${gamecount eq 0}">
 
	<div style="margin-left:auto; margin-right:auto; text-align:center; ">
		<h1>Round ${gamecount+1}</h1>
	</div>

  <form name="myform" method="post" action="${pageContext.request.contextPath}/Ideal/ideal_worldcup.do">
  
  
  <div style="margin-left:auto; margin-right:auto; text-align:center; ">
  
    <div style="float: left; width: 50%;">
	      <div>
		      <img src="${pageContext.request.contextPath}/assets/img/1.jpg">
		  </div>
		  <div style="margin-left:auto; margin-right:auto; text-align:center; ">
		      <label> <input type="radio" name="result1" value="1"> </label>
		  </div>
	</div>
	
    <div style="float: left; width: 50%;">
		<div>
		      <img src="${pageContext.request.contextPath}/assets/img/2.jpg">
		</div>
		<div style="margin-left:auto; margin-right:auto; text-align:center; ">
		      <label> <input type="radio" name="result1" value="2"> </label>
		</div>
	</div>
	
	
	
      <input type='hidden' name='g1' value='${g1}' />
      <input type='hidden' name='g2' value='${g2}' />
      <input type='hidden' name='result1' value='${result1}' />
      <input type='hidden' name='gamecount' value='${gamecount+1}' />

      <div id="btn_group">
      <button id="button2" type="submit">다음단계</button>
      </div>
  </div>

     
     <c:choose> 
     <c:when test="${gamecount eq 0}">
      
	     <c:choose> 
 	      <c:when test="${result1 eq 1}">
	          <c:set var="g1" value='${g1+1}'/>
	      </c:when>
          
          <c:when test="${result1 eq 2}">
	          <c:set var="g2" value='${g2+1}'/>
	      </c:when>
          </c:choose>

   </c:when>
   </c:choose>

   </form>
   
</c:when>   
 <c:when test="${(gamecount gt 0) and (gamecount lt 8)}">

   <div style="margin-left:auto; margin-right:auto; text-align:center; ">
		<h1>Round ${gamecount+1}</h1>
	</div>

   <form name="myform" method="post" action="${pageContext.request.contextPath}/Ideal/ideal_worldcup.do">
      
      
      <div style="margin-left:auto; margin-right:auto; text-align:center; ">
      	
	      	<div style="float: left; width: 50%;">
		      	<div>
			      	<img src="${pageContext.request.contextPath}/assets/img/${gamecount*2+1}.jpg">
			    </div>
			    <div style="margin-left:auto; margin-right:auto; text-align:center; ">
			      	<label> <input type="radio" name="result${gamecount+1}" value='${gamecount*2+1}'> </label>
			    </div>
		    </div>
		    
		    <div style="float: left; width: 50%;">
			    <div>
		      		<img src="${pageContext.request.contextPath}/assets/img/${gamecount*2+2}.jpg">
		      	</div>
		      	<div style="margin-left:auto; margin-right:auto; text-align:center; ">
		      		<label> <input type="radio" name="result${gamecount+1}" value='${gamecount*2+2}'> </label>
		      	</div>
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
   
      <div id="btn_group">
      <button id="button2" type="submit">다음단계</button>
      </div>
      </div>
      <c:choose>
      <c:when test="${gamecount eq 2}">
         <c:choose>
           <c:when test="${result3 eq 5}">
          	<c:set var="g5" value='${g5+1}'/>
           </c:when>
          
           <c:when test="${result3 eq 6}">
            <c:set var="g6" value='${g6+1}'/>
            </c:when>
          </c:choose>
      </c:when>
          
       <c:when test="${gamecount eq 3}">
       <c:choose>   
           <c:when test="${result4 eq 7}">
             <c:set var="g7" value='${g7+1}'/>
           </c:when>
           <c:when test="${result4 eq 8}">
             <c:set var="g8" value='${g8+1}'/>
           </c:when>
       </c:choose>
       </c:when>
          
       <c:when test="${gamecount eq 4}">
        <c:choose>  
           <c:when test="${result5 eq 9}">
             <c:set var="g9" value='${g9+1}'/>
           </c:when>
           <c:when test="${result5 eq 10}">
             <c:set var="g10" value='${g10+1}'/>
           </c:when>
         </c:choose>
        </c:when>
          
       <c:when test="${gamecount eq 5}">
       <c:choose>
          <c:when test="${result6 eq 11}">
             <c:set var="g11" value='${g11+1}'/>
             </c:when>
          <c:when test="${result6 eq 12}">
             <c:set var="g12" value='${g12+1}'/>
             </c:when>
          </c:choose>
          </c:when>
          
       <c:when test="${gamecount eq 6}">
         <c:choose>
          <c:when test="${result7 eq 13}">
            <c:set var="g13" value='${g13+1}'/>
             </c:when>
          <c:when test="${result7 eq 14}">
            <c:set var="g14" value='${g14+1}'/>
             </c:when>
          </c:choose>
          </c:when>
         
       <c:when test="${gamecount eq 7}">
         <c:choose>
         <c:when test="${result8 eq 15}">
             <c:set var="g15" value='${g15+1}'/>
         </c:when>
         <c:when test="${result8 eq 16}">
            <c:set var="g16" value='${g16+1}'/>
         </c:when>
         </c:choose>
         </c:when>
         
      </c:choose>
      
   </form>
   
   


</c:when>
<c:when test="${gamecount gt 8 and gamecount lt 15}">
   
   <div style="margin-left:auto; margin-right:auto; text-align:center; ">
		<h1>Round ${gamecount+1}</h1>
	</div>

   <form name="myform" method="post" action="${pageContext.request.contextPath}/Ideal/ideal_worldcup.do">
   

	<div style="margin-left:auto; margin-right:auto; text-align:center; ">		    
		 <div style="float: left; width: 50%;">
		   <div>
			   <img src="${pageContext.request.contextPath}/assets/img/${((gamecount)-7)*2-1}.jpg">
			</div>
			<div style="margin-left:auto; margin-right:auto; text-align:center; ">
			   <label> <input type="radio" name="result${gamecount+1}" value="result${((gamecount)-7)*2-1}"> </label> 
			</div>
		 </div>
			
		<div style="float: left; width: 50%; ">
			<div>   
			   <img src="${pageContext.request.contextPath}/assets/img/${((gamecount)-7)*2}.jpg">
			</div>
			<div style="margin-left:auto; margin-right:auto; text-align:center; ">
			   <label> <input type="radio" name="result${gamecount+1}" value="result${((gamecount)-7)*2}"> </label>
			</div>
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
      
     <div id="btn_group">
      <button id="button2" type="submit">다음단계</button>
      </div>
      </div>
      
 <c:choose>
 
    <c:when test="${gamecount eq 8}">
     	<c:choose>
	  	   <c:when test="${result9 eq 1}">
              <c:set var="g1" value='${g1+1}'/>
             </c:when>
           <c:when test="${result9 eq 2}">
              <c:set var="g2" value='${g2+1}'/>
             </c:when>
           <c:when test="${result9 eq 3}">
             <c:set var="g3" value='${g3+1}'/>
             </c:when>
           <c:when test="${result9 eq 4}">
             <c:set var="g4" value='${g4+1}'/>
             </c:when>
         </c:choose>
    </c:when>
           
 
 
   <c:when test="${gamecount eq 9}">
     	<c:choose>
           <c:when test="${result10 eq 5}">
             <c:set var="g5" value='${g5+1}'/>
             </c:when>
           <c:when test="${result10 eq 6}">
             <c:set var="g6" value='${g6+1}'/>
             </c:when>
           <c:when test="${result10 eq 7}">
             <c:set var="g7" value='${g7+1}'/>
             </c:when>
           <c:when test="${result10 eq 8}">
             <c:set var="g8" value='${g8+1}'/>
             </c:when>
         </c:choose>
    </c:when>
           
           
      <c:when test="${gamecount eq 10}">
      <c:choose>
           <c:when test="${result11 eq 9}">
             <c:set var="g9" value='${g9+1}'/>
             </c:when>
           <c:when test="${result11 eq 10}">
             <c:set var="g10" value='${g10+1}'/>
             </c:when>
           <c:when test="${result11 eq 11}">
             <c:set var="g11" value='${g11+1}'/>
             </c:when>
           <c:when test="${result11 eq 12}">
             <c:set var="g12" value='${g12+1}'/>
             </c:when>
       </c:choose>
       </c:when>
           
    <c:when test="${gamecount eq 11}">
    <c:choose>
           <c:when test="${result11 eq 13}">
             <c:set var="g13" value='${g13+1}'/>
             </c:when>
           <c:when test="${result11 eq 14}">
             <c:set var="g14" value='${g14+1}'/>
             </c:when>
           <c:when test="${result11 eq 15}">
             <c:set var="g15" value='${g15+1}'/>
             </c:when>
           <c:when test="${result11 eq 16}">
            <c:set var="g16" value='${g16+1}'/>
             </c:when>
    </c:choose>
    </c:when>
           
   <c:when test="${gamecount eq 12}">
   	<c:choose>
	  	   <c:when test="${result13 eq 1}">
              <c:set var="g1" value='${g1+1}'/>
             </c:when>
           <c:when test="${result13 eq 2}">
              <c:set var="g2" value='${g2+1}'/>
             </c:when>
           <c:when test="${result13 eq 3}">
             <c:set var="g3" value='${g3+1}'/>
             </c:when>
           <c:when test="${result13 eq 4}">
             <c:set var="g4" value='${g4+1}'/>
             </c:when>
           <c:when test="${result13 eq 5}">
             <c:set var="g5" value='${g5+1}'/>
             </c:when>
           <c:when test="${result13 eq 6}">
             <c:set var="g6" value='${g6+1}'/>
             </c:when>
           <c:when test="${result13 eq 7}">
             <c:set var="g7" value='${g7+1}'/>
             </c:when>
            <c:when test="${result13 eq 8}">
             <c:set var="g8" value='${g8+1}'/>
             </c:when>
 	</c:choose>   
  </c:when>
  
  <c:when test="${gamecount eq 13}">
  		<c:choose>
           <c:when test="${result14 eq 9}">
             <c:set var="g9" value='${g9+1}'/>
             </c:when>
           <c:when test="${result14 eq 10}">
             <c:set var="g10" value='${g10+1}'/>
             </c:when>
           <c:when test="${result14 eq 11}">
             <c:set var="g11" value='${g11+1}'/>
             </c:when>
           <c:when test="${result14 eq 12}">
             <c:set var="g12" value='${g12+1}'/>
             </c:when>
           <c:when test="${result14 eq 13}">
             <c:set var="g13" value='${g13+1}'/>
             </c:when>
           <c:when test="${result14 eq 14}">
             <c:set var="g14" value='${g14+1}'/>
             </c:when>
           <c:when test="${result14 eq 15}">
             <c:set var="g15" value='${g15+1}'/>
             </c:when>
            <c:when test="${result14 eq 16}">
            <c:set var="g16" value='${g16+1}'/>
             </c:when>
  		</c:choose>
  </c:when>
  
  <c:when test="${gamecount eq 14}">
       
       <c:choose>
	  	   <c:when test="${result15 eq 1}">
              <c:set var="g1" value='${g1+1}'/>
             </c:when>
           <c:when test="${result15 eq 2}">
              <c:set var="g2" value='${g2+1}'/>
             </c:when>
           <c:when test="${result15 eq 3}">
             <c:set var="g3" value='${g3+1}'/>
             </c:when>
           <c:when test="${result15 eq 4}">
             <c:set var="g4" value='${g4+1}'/>
             </c:when>
           <c:when test="${result15 eq 5}">
             <c:set var="g5" value='${g5+1}'/>
             </c:when>
           <c:when test="${result15 eq 6}">
             <c:set var="g6" value='${g6+1}'/>
             </c:when>
           <c:when test="${result15 eq 7}">
             <c:set var="g7" value='${g7+1}'/>
             </c:when>
            <c:when test="${result15 eq 8}">
             <c:set var="g8" value='${g8+1}'/>
             </c:when>
           <c:when test="${result15 eq 9}">
             <c:set var="g9" value='${g9+1}'/>
             </c:when>
           <c:when test="${result15 eq 10}">
             <c:set var="g10" value='${g10+1}'/>
             </c:when>
           <c:when test="${result15 eq 11}">
             <c:set var="g11" value='${g11+1}'/>
             </c:when>
           <c:when test="${result13 eq 12}">
             <c:set var="g12" value='${g12+1}'/>
             </c:when>
           <c:when test="${result13 eq 13}">
             <c:set var="g13" value='${g13+1}'/>
             </c:when>
           <c:when test="${result13 eq 14}">
             <c:set var="g14" value='${g14+1}'/>
             </c:when>
           <c:when test="${result13 eq 15}">
             <c:set var="g15" value='${g15+1}'/>
             </c:when>
            <c:when test="${result13 eq 16}">
            <c:set var="g16" value='${g16+1}'/>
             </c:when>
       		 </c:choose>
     </c:when>
     </c:choose>
     </form>
</c:when>           
   


	<c:when test="${gamecount eq 15}">

      <form name="myform" method="post" action="${pageContext.request.contextPath}/Ideal/ideal_worldcup.do">
      <input type='hidden' name='gamecount' value='${gamecount}' />
      <input type='hidden' name='result15' value='${result15}' />
      
     <c:set />
     
     <c:choose> 
     <c:when test="${result15 eq 1}"><c:set var="menu" value="치킨"/></c:when>
     <c:when test="${result15 eq 2}"><c:set var="menu" value="피자"/></c:when>
     <c:when test="${result15 eq 3}"><c:set var="menu" value="국밥"/></c:when>
     <c:when test="${result15 eq 4}"><c:set var="menu" value="떡볶이"/></c:when>
     <c:when test="${result15 eq 5}"><c:set var="menu" value="삼겹살"/></c:when>
     <c:when test="${result15 eq 6}"><c:set var="menu" value="회"/></c:when>
     <c:when test="${result15 eq 7}"><c:set var="menu" value="초밥"/></c:when>
     <c:when test="${result15 eq 8}"><c:set var="menu" value="마라탕"/></c:when>
     <c:when test="${result15 eq 9}"><c:set var="menu" value="스테이크"/></c:when>
     <c:when test="${result15 eq 10}"><c:set var="menu" value="버거"/></c:when>
     <c:when test="${result15 eq 11}"><c:set var="menu" value="짜장면"/></c:when>
     <c:when test="${result15 eq 12}"><c:set var="menu" value="생선구이"/></c:when>
     <c:when test="${result15 eq 13}"><c:set var="menu" value="타코"/></c:when>
     <c:when test="${result15 eq 14}"><c:set var="menu" value="곱창"/></c:when>
     <c:when test="${result15 eq 15}"><c:set var="menu" value="만두"/></c:when>
     <c:when test="${result15 eq 16}"><c:set var="menu" value="족발"/></c:when> 
     </c:choose>
      
	<div style="margin-left:auto; margin-right:auto; text-align:center; ">
		<h1>최종 선택 결과 : ${menu}</h1>
	</div>
	   <div style="margin-left:auto; margin-right:auto; text-align:center; ">
	   	<img src="${pageContext.request.contextPath}/assets/img/${result15}.jpg">
	   </div>
   	</form>
	</c:when>
</c:choose>     
</body>
</html>