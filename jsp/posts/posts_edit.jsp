<%@page import="java.util.ArrayList"%>
<%@page import="com.yousns.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:useBean id="postDAO" class="com.yousns.dao.PostDAO" scope="page" />
<%
	String postKey = (String) session.getAttribute("postKey");
	String content = request.getParameter("content");
	String utubelink = request.getParameter("utubelink");
	String[] gallery = request.getParameterValues("gallery");
	ArrayList<String> glist = new ArrayList<>();
	for (String g : gallery)
		glist.add(g);

	boolean success = postDAO.editPost(postKey, content, utubelink, glist);
	if (success) {
%>
<script type="text/javascript">
	alert('게시물 수정 완료.');
	location.href = '/posts/<%=postKey%>';
</script>
<%
	} else {
%>
<script type="text/javascript">
	alert('게시물 수정 실패');
	location.href = '/posts';
</script>
<%
	}
%>