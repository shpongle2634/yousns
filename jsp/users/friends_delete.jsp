<%@page import="com.yousns.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:useBean id="userDAO" class="com.yousns.dao.UserDAO" scope="page" />
<%
	String userKey = (String) session.getAttribute("userKey");
	String friendKey = (String)request.getAttribute("fid");

	boolean success = userDAO.deleteFriend(userKey, friendKey);

	if (success) {
%>
<script type="text/javascript"> alert('친구삭제 완료.'); 
				location.href='/yousns/users/<%=userKey%>/friends';
</script>
<%
	} else {
%>
<script type="text/javascript"> alert('친구삭제 실패'); 
			location.href='/yousns/users/<%=userKey%>/friends';
</script>
<%
	}
%>