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
 
<% if(gamecount == 0){ %>

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
      
     <%
     
     if(gamecount == 0){ 
          if ( result1 == 1) {
            g1 = g1+1;
          } else if(result1 == 2) {
            g2 = g2+1;
          }
    }
     %>

   </form>
   
   <!-- ★★★★★★★★★★★★2nd to 8th game★★★★★★★★★★★★★ -->

<%
   } else if( 0<gamecount && gamecount<8 ){
      

            
%>
   <h1>Round <%=gamecount+1 %></h1>

   <form name="myform" method="post" action="ideal_worldcup.jsp">
      

   <!-- 이미지 띄우기 -->
      <div>
         <img src=img/<%=((gamecount)*2+1) %>.jpg>
         <img src=img/<%=((gamecount)*2+2) %>.jpg>
      </div>
      
   <!--  라디오버튼 만들기 -->   
      <div>
         <label> <input type="radio" name="result<%=gamecount+1 %>" value=<%=((gamecount)*2+1) %>> <%=(gamecount)*2+1 %>  </label> <!-- 3, 5, 7, 9, 11, 13, 15 -->
         <label> <input type="radio" name="result<%=gamecount+1 %>" value=<%=((gamecount)*2+2) %>> <%=(gamecount)*2+2 %>  </label> <!-- 4, 6, 8, 10, 12, 14, 16 -->
      </div>
   
   <!-- 값 보내기 -->
   
 
      <input type='hidden' name='gamecount' value='<%=gamecount+1 %>' />
      
      <input type='hidden' name='result1' value='<%=result1%>' />
      <input type='hidden' name='result2' value='<%=result2%>' />
      <input type='hidden' name='result3' value='<%=result3%>' />
      <input type='hidden' name='result4' value='<%=result4%>' />
      <input type='hidden' name='result5' value='<%=result5%>' />
      <input type='hidden' name='result6' value='<%=result6%>' />
      <input type='hidden' name='result7' value='<%=result7%>' />
      <input type='hidden' name='result8' value='<%=result8%>' />
      
      <input type='hidden' name='g1' value='<%=g1%>' />
      <input type='hidden' name='g2' value='<%=g2%>' />
      <input type='hidden' name='g3' value='<%=g3%>' />
      <input type='hidden' name='g4' value='<%=g4%>' />
      <input type='hidden' name='g5' value='<%=g5%>' />
      <input type='hidden' name='g6' value='<%=g6%>' />
      <input type='hidden' name='g7' value='<%=g7%>' />
      <input type='hidden' name='g8' value='<%=g8%>' />
      <input type='hidden' name='g9' value='<%=g9%>' />
      <input type='hidden' name='g10' value='<%=g10%>' />
      <input type='hidden' name='g11' value='<%=g11%>' />
      <input type='hidden' name='g12' value='<%=g12%>' />
      <input type='hidden' name='g13' value='<%=g13%>' />
      <input type='hidden' name='g14' value='<%=g14%>' />
      <input type='hidden' name='g15' value='<%=g15%>' />
      <input type='hidden' name='g16' value='<%=g16%>' />
   
      <button type="submit">다음단계</button>
      
      <%
      if(gamecount == 2){   
          if (result3 == 5) {
             g5 = g5+1;
          } else if(result3 == 6) {
             g6 = g6+1;
          }
          
       } else if(gamecount == 3){   
          if (result4 == 7) {
             g7 = g7+1;
          } else if(result4 == 8) {
             g8 = g8+1;
          }
          
       } else if(gamecount == 4){
          if (result5 == 9) {
             g9 = g9+1;
          } else if(result5 == 10) {
             g10 = g10+1;
          }
          
       } else if(gamecount == 5){
          if (result6 == 11) {
             g11 = g11+1;
          } else if( result6 == 12){
             g12 = g12+1;
          }
          
       } else if(gamecount == 6){
          if (result7 == 13) {
             g13 = g13+1;
          } else if (result7 ==14) {
             g14 = g14+1;
          }
         
       } else if(gamecount == 7){
          if (result8 == 15) {
             g15 = g15+1;
          } else if (result8 == 16) {
             g16 = g16+1;
          }
       }
      
      %>
      
   </form>
   
   

 <!-- ★★★★★★★★★★★★9th to 16th★★★★★★★★★★★★★ -->   
<%
}else if( 8<= gamecount &&  gamecount < 15 ){
   
   String rs1 = "result"+String.valueOf((web.getInt("gamecount")-7)*2-1);
   String rs2 = "result"+String.valueOf((web.getInt("gamecount")-7)*2);   
%>
   
   <h1>Round <%=gamecount+1 %></h1>

   <form name="myform" method="post" action="ideal_worldcup.jsp">

   <div>
       <img src=img/<%=web.getInt(rs1) %>.jpg>    
      <img src=img/<%=web.getInt(rs2) %>.jpg>
   </div>

         
   <!--  라디오버튼 만들기 -->

   <div>
       <label> <input type="radio" name="result<%=gamecount+1 %>" value=<%=web.getInt(rs1) %>> <%=web.getInt(rs1) %> </label> 
       <label> <input type="radio" name="result<%=gamecount+1 %>" value=<%=web.getInt(rs2) %>> <%=web.getInt(rs2) %> </label>
    </div>    

      <input type='hidden' name='gamecount' value='<%=gamecount+1 %>' />
      
      <input type='hidden' name='result1' value='<%=result1%>' />
      <input type='hidden' name='result2' value='<%=result2%>' />
      <input type='hidden' name='result3' value='<%=result3%>' />
      <input type='hidden' name='result4' value='<%=result4%>' />
      <input type='hidden' name='result5' value='<%=result5%>' />
      <input type='hidden' name='result6' value='<%=result6%>' />
      <input type='hidden' name='result7' value='<%=result7%>' />
      <input type='hidden' name='result8' value='<%=result8%>' />
      <input type='hidden' name='result9' value='<%=result9%>' />
      <input type='hidden' name='result10' value='<%=result10%>' />
      <input type='hidden' name='result11' value='<%=result11%>' />
      <input type='hidden' name='result12' value='<%=result12%>' />
      <input type='hidden' name='result13' value='<%=result13%>' />
      <input type='hidden' name='result14' value='<%=result14%>' />
      <input type='hidden' name='result15' value='<%=result15%>' />

      <input type='hidden' name='g1' value='<%=g1%>' />
      <input type='hidden' name='g2' value='<%=g2%>' />
      <input type='hidden' name='g3' value='<%=g3%>' />
      <input type='hidden' name='g4' value='<%=g4%>' />
      <input type='hidden' name='g5' value='<%=g5%>' />
      <input type='hidden' name='g6' value='<%=g6%>' />
      <input type='hidden' name='g7' value='<%=g7%>' />
      <input type='hidden' name='g8' value='<%=g8%>' />
      <input type='hidden' name='g9' value='<%=g9%>' />
      <input type='hidden' name='g10' value='<%=g10%>' />
      <input type='hidden' name='g11' value='<%=g11%>' />
      <input type='hidden' name='g12' value='<%=g12%>' />
      <input type='hidden' name='g13' value='<%=g13%>' />
      <input type='hidden' name='g14' value='<%=g14%>' />
      <input type='hidden' name='g15' value='<%=g15%>' />
      <input type='hidden' name='g16' value='<%=g16%>' />

   <button type="submit">다음단계</button>
   
   <%
   if(gamecount == 9){
     
      if (result10 == 5) {
              g5 = g5+1;
           } else if (result10 == 6) {
              g6 = g6+1;
           } else if (result10 == 7) {
             g7 = g7+1;
           } else if (result10 == 8) {
             g8 = g8+1;
           }
           
   }   else if(gamecount == 10){
        if (result11 == 9) {
              g9 = g9+1;
           } else if (result11 == 10) {
              g10 = g10+1;
           } else if (result11 == 11) {
             g11 = g11+1;
           } else if (result11 == 12) {
             g12 = g12+1;
           }
           
  }  else if(gamecount == 11){
        if (result12 == 13) {
              g13 = g13+1;
           } else if (result12 == 14) {
              g14 = g14+1;
           } else if (result12 == 15) {
             g15 = g15+1;
           } else if (result12 == 16) {
             g16 = g16+1;
           }
           
  } else if(gamecount == 12){
        if (result13 == 1) {
              g1 = g1+1;
           } else if (result13 == 2) {
              g2 = g2+1;
           } else if (result13 == 3) {
             g3 = g3+1;
           } else if (result13 == 4) {
             g4 = g4+1;
           } else if (result13 == 5) {
             g5 = g5+1;
           } else if (result13 == 6) {
             g6 = g6+1;
           } else if (result13 == 7) {
             g7 = g7+1;
            } else if (result13 == 8) {
             g8 = g8+1;
           }
           
  } else if(gamecount == 13){
        if (result14 == 9) {
              g9 = g9+1;
           } else if (result13 == 10) {
              g10 = g10+1;
           } else if (result13 == 11) {
             g11 = g11+1;
           } else if (result13 == 12) {
             g12 = g12+1;
           } else if (result13 == 13) {
             g13 = g13+1;
           } else if (result13 == 14) {
             g14 = g14+1;
           } else if (result13 == 15) {
             g15 = g15+1;
            } else if (result13 == 16) {
             g16 = g16+1;
           }
           
  } else if(gamecount == 14){
        if (result15 == 1) {
              g1 = g1+1;
           } else if (result15 == 2) {
              g2 = g2+1;
           } else if (result15 == 3) {
             g3 = g3+1;
           } else if (result15 == 4) {
             g4 = g4+1;
           } else if (result15 == 5) {
             g5 = g5+1;
           } else if (result15 == 6) {
             g6 = g6+1;
           } else if (result15 == 7) {
             g7 = g7+1;
            } else if (result15 == 8) {
             g8 = g8+1;
           } else if (result15 == 9) {
             g9 = g9+1;
           } else if (result15 == 10) {
             g10 = g10+1;
           } else if (result15 == 11) {
             g11 = g11+1;      
           } else if (result13 == 12) {
             g12 = g12+1;
           } else if (result13 == 13) {
             g13 = g13+1;
           } else if (result13 == 14) {
             g14 = g14+1;
           } else if (result13 == 15) {
             g15 = g15+1;
            } else if (result13 == 16) {
             g16 = g16+1;
           }
  }        

   %>
</form>
 <!-- ★★★★★★★★★★★★Finish★★★★★★★★★★★★★ -->
   <%
      } else if(gamecount==15) {        
      %>
      <form name="myform" method="post" action="ideal_worldcup.jsp">
      <input type='hidden' name='gamecount' value='<%=gamecount %>' />
      <input type='hidden' name='result15' value='<%=result15%>' />
      <% 
         String menu = web.getString("menu");
      
      if(result15 == 1){menu = "치킨";}
      else if(result15 == 2){menu = "피자";}
      else if(result15 == 3){menu = "국밥";}
      else if(result15 == 4){menu = "떡볶이";}
      else if(result15 == 5){menu = "삼겹살";}
      else if(result15 == 6){menu = "회";}
      else if(result15 == 7){menu = "초밥";}
      else if(result15 == 8){menu = "마라탕";}
      else if(result15 == 9){menu = "스테이크";}
      else if(result15 == 10){menu = "버거";}
      else if(result15 == 11){menu = "짜장면";}
      else if(result15 == 12){menu = "생선구이";}
      else if(result15 == 13){menu = "타코";}
      else if(result15 == 14){menu = "곱창";}
      else if(result15 == 15){menu = "만두";}
      else if(result15 == 16){menu = "족발";} 
      %>
      
<!--       <button type="submit">다음단계</button>  -->      
    <h1>최종 선택 결과 : <%=menu %></h1>
   <img src=img/<%=web.getInt("result15") %>.jpg>
 <% } %>
    </form>

</body>
</html>