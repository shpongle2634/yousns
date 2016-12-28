<%@page import="java.util.ArrayList"%>
<%@page import="com.yousns.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<jsp:useBean id="pageDAO" class="com.yousns.dao.PageDAO" scope="page" />
<%
	String userKey = (String) session.getAttribute("userKey");

	String pageName = request.getParameter("pageName");
	
	boolean success = pageDAO.newpage(userKey, pageName);
	if (success) {
%>
<script type="text/javascript">
	alert('페이지생성완료 ');
	location.href = 'pages';
</script>
<%
	} else {
%>
<script type="text/javascript">
	alert('페이지 생성 실패');
	location.href = 'pages';
</script>
<%
	}
%>