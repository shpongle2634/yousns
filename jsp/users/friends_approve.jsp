<%@page import="com.yousns.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:useBean id="userDAO" class="com.yousns.dao.UserDAO" scope="page" />
<%
	String userKey = (String) session.getAttribute("userKey");
	String friendKey = request.getParameter("fid");

	boolean success = userDAO.approveFriend(userKey, friendKey);

	if (success) {
%>
<script type="text/javascript"> alert('친구수락 완료.'); 
				location.href='/users/<%=userKey%>/friends';
</script>
<%
	} else {
%>
<script type="text/javascript"> alert('친구수락 실패'); 
			location.href='/users/<%=userKey%>
	/friends';
</script>
<%
	}
%>