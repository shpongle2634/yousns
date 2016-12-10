<%@page import="java.util.ArrayList"%>
<%@page import="com.yousns.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:useBean id="commentDAO" class="com.yousns.dao.CommentDAO" scope="page" />
<%
	String commentKey = (String) session.getAttribute("commentKey");
	String content = request.getParameter("content");

	boolean success = commentDAO.updateComment(commentKey, content);
	if (success) {
%>
<script type="text/javascript">
	alert('댓글 수정 완료.');
	location.href = '/posts';
</script>
<%
	} else {
%>
<script type="text/javascript">
	alert('댓글 수정 실패');
	location.href = '/posts';
</script>
<%
	}
%>