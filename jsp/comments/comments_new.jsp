<%@page import="java.util.ArrayList"%>
<%@page import="com.yousns.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:useBean id="commentDAO" class="com.yousns.dao.CommentDAO" scope="page" />
<%
	String postKey = (String)request.getAttribute("postKey");
	String userKey = (String) session.getAttribute("userKey");
	String content = request.getParameter("content");

	boolean success = commentDAO.newComment(postKey, userKey, content);
	if (success) {
%>
<script type="text/javascript">
	alert('댓글 등록 완료.');
	location.href = 'posts/<%=postKey%>';
</script>
<%
	} else {
%>
<script type="text/javascript">
	alert('댓글 등록 실패');
	location.href = 'posts/<%=postKey%>';
</script>
<%
	}
%>