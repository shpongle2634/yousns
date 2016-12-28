<%@page import="com.yousns.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:useBean id="userDAO" class="com.yousns.dao.UserDAO" scope="page" />
<%
	String id = request.getParameter("id");
	String password = request.getParameter("password");

	if (session.getAttribute("token") == null) {
		UserVO vo = userDAO.login(id, password);

		if (vo != null) {
			String token = userDAO.generateToken();
			session.setAttribute("token", token);
			session.setAttribute("userName", vo.getName());
			session.setAttribute("userKey", vo.getUserKey());
			response.sendRedirect("/yousns/posts");
		} else {
%>
<script>alert('사용자 정보가 일치하지않습니다.');history.back();</script>

<%
	}
	}else {
		session.invalidate();
		response.sendRedirect("/yousns");
	}
%>