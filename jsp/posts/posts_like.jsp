<%@page import="java.util.ArrayList"%>
<%@page import="com.yousns.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:useBean id="postDAO" class="com.yousns.dao.PostDAO" scope="page" />
<%
	String postKey = (String)request.getAttribute("postKey");


	String userKey = (String) session.getAttribute("userKey");

	boolean success = postDAO.likePost(postKey, userKey);
	if (success) {
%>
<script type="text/javascript">
	location.href='/yousns/posts';
</script>
<%
	} else {
%>
<script type="text/javascript">
	location.href='/yousns/posts';
</script>
<%
	}
%>