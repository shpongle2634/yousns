<%@page import="java.util.ArrayList"%>
<%@page import="com.yousns.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:useBean id="commentDAO" class="com.yousns.dao.CommentDAO" scope="page" />
<%
	String commentKey = (String) request.getAttribute("commentkey");
	String userKey = (String) session.getAttribute("userKey");
	boolean success = commentDAO.likeComment(commentKey, userKey);
	if (success) {
%>
<script type="text/javascript">
	history.back();
</script>
<%
	} else {
%>
<script type="text/javascript">
	history.back();
</script>
<%
	}
%>