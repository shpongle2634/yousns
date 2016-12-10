<%@page import="com.yousns.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:useBean id="userDAO" class="com.yousns.dao.UserDAO" scope="page" />
<%
	String userKey= (String) session.getAttribute("useKey");
	String Email = request.getParameter("Email");
	String Pwd = request.getParameter("Pwd");
	String Name = request.getParameter("Name");
	String Gender = request.getParameter("Gender");

	boolean success = userDAO.updateUser(userKey, Email, Pwd, Name, Short.parseShort(Gender));
	if (success) {
%>
<script type="text/javascript">
	alert('정보수정 완료.');
	location.href = '/users/<%=userKey%>/details';
</script>
<%
	} else {
%>
<script type="text/javascript">
	alert('정보수정 실패');
	location.href = '/users/<%=userKey%>/details';
</script>
<%
	}
%>