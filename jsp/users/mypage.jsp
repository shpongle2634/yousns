<%@page import="com.yousns.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!doctype html>
<%@include file="../header.jsp"%>
<!-- body -->
<jsp:useBean id="userDAO" class="com.yousns.dao.UserDAO"></jsp:useBean>>

<%
	String userkey = (String)session.getAttribute("userKey");
	UserVO vo = userDAO.myPage(userkey);
	
%>
<div class="wrapper">
   <div class="content2">
      <img src="\yousns\jsp\image\mypage.png" width="40px" height="40px"
         style="padding-bottom: 0px;"><span
         style="font-size: 20pt; margin: 0px 5px 0px 5px; color: #38baeb; padding-bottom: 2px;"><strong>마이
            페이지</strong></span>
   </div>
</div>

<div class="wrapper">
   <div class="content3" style="background:#fff; padding-bottom:0px">
      <div class="left-info">
         <div class="infoform">
            <div class="infophoto" style='display: table-cell; vertical-align: middle'>
               E-Mail
            </div>
            <div class="infoname"
               style='display: table-cell; vertical-align: middle'>
               <strong><%=vo.getEmail()%></strong>
            </div>
         </div>
      </div>
      <div class="left-info">
         <div class="infoform">
            <div class="infophoto">
               Name
            </div>
            <div class="infoname">
               <strong><%=vo.getName() %></strong>
            </div>
         </div>
      </div>
      <div class="left-info">
         <div class="infoform">
            <div class="infophoto">
               Password
            </div>
            <form action="/yousns/users/<%=vo.getUserKey()%>/edit" method="post" >
            <div class="infoname"
               style='display: table-cell; vertical-align: middle'>
               <strong><input size="20" name="Pwd" type="text" placeholder="********" style="text-align=left;"></strong>
            </div>
            <div class="infobtn">
            	
              		<button type="submit" style="background-color: white"><img src="\yousns\jsp\image\change.png" width="50px" height="35px"></button> 
              
            </div>
             </form>
         </div>
      </div>
      <div class="left-info">
         <div class="infoform">
            <div class="infophoto">
               Gender
            </div>
            <div class="infoname">
               <%=vo.getGender()==0?"남":"여"%>
            </div>
         </div>
      </div>
   </div>
</div>

</body>
</html>
