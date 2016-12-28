<%@page import="java.util.ArrayList"%>
<%@page import="com.yousns.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<jsp:useBean id="pageDAO" class="com.yousns.dao.PageDAO" scope="page" />
<%
	String pagekey = (String)request.getAttribute("pageKey");
	
	boolean success = pageDAO.deletepage(pagekey);
	if (success) {
%>
<script type="text/javascript">
	alert('페이지 삭제');
	location.href = '/yousns/pages';
</script>
<%
	} else {
%>
<script type="text/javascript">
	alert('페이지 삭제 실패');
	location.href = '/yousns/pages';
</script>
<%
	}
%>