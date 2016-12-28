<%@page import="java.util.ArrayList"%>
<%@page import="com.yousns.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<jsp:useBean id="postDAO" class="com.yousns.dao.PostDAO" scope="page" />
<%
	String userKey = (String) session.getAttribute("userKey");
	String content = request.getParameter("content");
	String pageKey = request.getParameter("pageKey");
	String groupKey = request.getParameter("groupKey");
	String utubelink = request.getParameter("utubeLink");
	if(utubelink.contains("watch?v="))
		utubelink=utubelink.replace("watch?v=", "embed/");
	String tags = null;
	if (content != null) {
		String[] tmp = content.split("#");
		if (tmp.length != 0) {
			tags = "";
			for (String t : tmp) {
				if (t.indexOf(" ") != -1)
				tags += ("#" + t.substring(0,t.indexOf(" ")));
				else
					tags += ("#" + t);	
			}
		}
	}else content="";
	String[] gallery = request.getParameterValues("gallery");
	ArrayList<String> glist = new ArrayList<String>();
	if (gallery != null) {
		for (String g : gallery)
			glist.add(g);
	}
	boolean success = postDAO.newPost(userKey, content, pageKey, groupKey, utubelink, tags, glist);
	if (success) {
%>
<script type="text/javascript">
	alert('게시물 등록 완료.');
	location.href = 'posts';
</script>
<%
	} else {
%>
<script type="text/javascript">
	alert('게시물 등록 실패');
	location.href = 'posts';
</script>
<%
	}
%>