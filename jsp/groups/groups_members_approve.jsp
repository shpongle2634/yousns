<%@page import="java.util.ArrayList"%>
<%@page import="com.yousns.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<jsp:useBean id="groupDAO" class="com.yousns.dao.GroupDAO" scope="page" />
<%
	String groupkey = (String)request.getAttribute("groupKey");
	String userkey =(String)request.getAttribute("memberkey");
	
	boolean success = groupDAO.approve_member(userkey, groupkey);
	if (success) {
%>
<script type="text/javascript">
	alert('그룹 가입 승인');
	location.href = '/yousns/groups';
</script>
<%
	} else {
%>
<script type="text/javascript">
	alert('그룹 승인 실패');
	location.href = '/yousns/groups';
</script>
<%
	}
%>