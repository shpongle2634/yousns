<%@page import="java.util.ArrayList"%>
<%@page import="com.yousns.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:useBean id="commentDAO" class="com.yousns.dao.CommentDAO" scope="page" />
<%
	String commentKey = request.getParameter("commentKey");
	boolean success = commentDAO.deleteComment(commentKey);
	if (success) {
%>
<script type="text/javascript">
	alert('댓글 삭제 완료.');
	location.href = '/posts';
</script>
<%
	} else {
%>
<script type="text/javascript">
	alert('댓글 삭제 실패');
	location.href = '/posts';
</script>
<%
	}
%>