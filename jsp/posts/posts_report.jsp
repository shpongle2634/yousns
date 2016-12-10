<%@page import="java.util.ArrayList"%>
<%@page import="com.yousns.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:useBean id="postDAO" class="com.yousns.dao.PostDAO" scope="page" />
<%
	String postKey = request.getParameter("postKey");
	String userKey = (String) session.getAttribute("userKey");
	String content = request.getParameter("content");
	String reportflag = request.getParameter("reportFlag");
	boolean success = postDAO.reportPost(postKey, userKey, content, Short.parseShort(reportflag));
	if (success) {
%>
<script type="text/javascript">
	alert('게시물 신고 완료.');
	location.href = '/posts/<%=postKey%>';
</script>
<%
	} else {
%>
<script type="text/javascript">
	alert('게시물 신고 실패');
	location.href = '/posts';
</script>
<%
	}
%>