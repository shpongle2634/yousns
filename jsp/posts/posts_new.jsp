<%@page import="java.util.ArrayList"%>
<%@page import="com.yousns.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:useBean id="postDAO" class="com.yousns.dao.PostDAO" scope="page" />
<%
	String userKey = (String) session.getAttribute("userKey");
	String content = request.getParameter("content");
	String utubelink = request.getParameter("utubelink");
	String[] gallery = request.getParameterValues("gallery");
	ArrayList<String> glist = new ArrayList<>();
	for (String g : gallery)
		glist.add(g);

	boolean success = postDAO.newPost(userKey, content, utubelink, glist);
	if (success) {
%>
<script type="text/javascript">
	alert('게시물 등록 완료.');
	location.href = '/posts';
</script>
<%
	} else {
%>
<script type="text/javascript">
	alert('게시물 등록 실패');
	location.href = '/posts';
</script>
<%
	}
%>