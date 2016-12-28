<%@page import="java.util.ArrayList"%>
<%@page import="com.yousns.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:useBean id="postDAO" class="com.yousns.dao.PostDAO" scope="page" />
<%
	String postKey = (String)request.getAttribute("postKey");
	boolean success = postDAO.deletePost(postKey);
	if (success) {
%>
<script type="text/javascript">
	alert('게시물 삭제 완료.');
	location.href = '/yousns/posts';
</script>
<%
	} else {
%>
<script type="text/javascript">
	alert('게시물 삭제 실패');
	location.href = '/yousns/posts';
</script>
<%
	}
%>