<%@page import="com.yousns.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:useBean id="userDAO" class="com.yousns.dao.UserDAO" scope="page" />
<%
	String userKey= (String) session.getAttribute("useKey");

	boolean success = userDAO.remove_user(userKey);
	if (success) {
%>
<script type="text/javascript">
	alert('회원탈퇴 완료.');
	location.href = '/';
</script>
<%
	} else {
%>
<script type="text/javascript">
	alert('회원탈퇴 실패');
	location.href = '/';
</script>
<%
	}
%>