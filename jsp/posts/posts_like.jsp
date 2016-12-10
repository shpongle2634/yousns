<%@page import="java.util.ArrayList"%>
<%@page import="com.yousns.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:useBean id="postDAO" class="com.yousns.dao.PostDAO" scope="page" />
<%
	String postKey = request.getParameter("postKey");
	String userKey = (String) session.getAttribute("userKey");
	boolean success = postDAO.likePost(postKey, userKey);
	if (success) {
%>
<script type="text/javascript">
	alert('좋아요 완료.');
	history.back();
</script>
<%
	} else {
%>
<script type="text/javascript">
	alert('좋아요 실패');
	history.back();
</script>
<%
	}
%>