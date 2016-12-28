<%@page import="com.yousns.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:useBean id="userDAO" class="com.yousns.dao.UserDAO" scope="page" />
<%
	String Email = request.getParameter("id");
	String Pwd = request.getParameter("password");
	String Name = request.getParameter("name");
	String Gender = request.getParameter("gender");

	boolean success = userDAO.join(Email, Pwd, Name, Short.parseShort(Gender));
	if (success) {
%>
<script type="text/javascript">
	alert('회원가입 완료.');
	location.href = '/yousns';
</script>
<%
	} else {
%>
<script type="text/javascript">
	alert('회원가입 실패');
	location.href = '/yousns';
</script>
<%
	}
%>