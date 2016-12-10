<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>YouSns</title>
</head>
<body>

 body :  <%=request.getParameter("body")%> <br>
 id : ${id}<br>
 session name : <%=session.getAttribute("name")%><br>
 voTest : ${StudentVO.s_name}<br> ${StudentVO.s_id}<br> ${StudentVO.s_pwd} <br>${StudentVO.s_addr}
 
 <form method="POST" action="http://localhost:8080/yousns/users/auth">
 	<input type="text" id="id"/>
 	<input type="password" id="password"/>
 	<input type="submit" value="login">
 </form>
 
</body>
</html>