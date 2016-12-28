<%@page import="com.yousns.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:useBean id="userDAO" class="com.yousns.dao.UserDAO" scope="page" />
<%
	String userKey= (String) session.getAttribute("userKey");
	String Email = request.getParameter("Email");
	String Pwd = request.getParameter("Pwd");
	String Name = request.getParameter("Name");
	String Gender = request.getParameter("Gender");

	boolean success = userDAO.updateUser(userKey, Pwd);
	if (success) {
%>
<script type="text/javascript">
	alert('정보수정 완료.');
	location.href = '/yousns/users/<%=userKey%>/edit';
</script>
<%
	} else {
%>
<script type="text/javascript">
	alert('정보수정 실패');
	location.href = '/yousns/users/<%=userKey%>/edit';
</script>
<%
	}
%>