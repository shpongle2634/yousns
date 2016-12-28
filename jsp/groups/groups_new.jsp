<%@page import="java.util.ArrayList"%>
<%@page import="com.yousns.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<jsp:useBean id="groupDAO" class="com.yousns.dao.GroupDAO" scope="page" />
<%
	String userKey = (String) session.getAttribute("userKey");

	String groupName = request.getParameter("groupName");
	
	boolean success = groupDAO.newgroup(userKey, groupName);
	if (success) {
%>
<script type="text/javascript">
	alert('그룹생성완료 ');
	location.href = 'groups';
</script>
<%
	} else {
%>
<script type="text/javascript">
	alert('그룹 생성 실패');
	location.href = 'groups';
</script>
<%
	}
%>